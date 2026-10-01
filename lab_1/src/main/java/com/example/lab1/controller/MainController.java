package com.example.lab1.controller;

import com.example.lab1.database.model.Player;
import com.example.lab1.database.repository.PlayerRepository;
import com.example.playerservice.grpc.HistoryRequest;
import com.example.playerservice.grpc.HistoryResponse;
import com.example.playerservice.grpc.PlayerServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

  // 1. Внедряем gRPC-клиент (клиентский стаб)
  @GrpcClient("player-service")
  private PlayerServiceGrpc.PlayerServiceBlockingStub playerServiceStub;

  @GetMapping("/")
  public String index(
          @RequestParam(required = false) String position,
          @RequestParam(required = false) Integer birthYearFrom,
          @RequestParam(required = false) Integer birthYearTo,
          @RequestParam(required = false) Integer weightFrom,
          @RequestParam(required = false) Integer weightTo,
          @RequestParam(required = false) Integer heightFrom,
          @RequestParam(required = false) Integer heightTo,
          Model model)
  {
    boolean hasFilters = (position != null || birthYearFrom != null || birthYearTo != null ||
            weightFrom != null || weightTo != null || heightFrom != null || heightTo != null);
    List<Player> players;
    if (hasFilters)
    {
      players = PlayerRepository.findByFilters(position, birthYearFrom, birthYearTo, weightFrom, weightTo, heightFrom, heightTo);
    }
    else
    {
      players = PlayerRepository.findAll();
    }
    model.addAttribute("players", players);
    return "index";
  }

  @GetMapping("/player/{id}")
  public String player (@PathVariable String id, Model model)
  {
    // Данные самого игрока и фото берем как и раньше из локальной БД
    Player player = PlayerRepository.findPlayer(id);
    String photo = PlayerRepository.getPhoto(player);

    // 2. Вместо обращения к local PlayerHistoryRepository делаем запрос по gRPC:
    HistoryRequest request = HistoryRequest.newBuilder()
            .setPlayerId(id)
            .build();

    // 3. Вызываем микросервис
    HistoryResponse response = playerServiceStub.getPlayerHistory(request);

    model.addAttribute("player", player);
    model.addAttribute("photo", photo);

    // 4. Передаем полученный список history в HTML-шаблон
    model.addAttribute("playerHistory", response.getHistoryList());

    return "player";
  }
}