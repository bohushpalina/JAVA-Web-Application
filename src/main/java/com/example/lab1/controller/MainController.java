package com.example.lab1.controller;

import com.example.lab1.database.model.Player;
import com.example.lab1.database.model.PlayerHistory;
import com.example.lab1.database.repository.PlayerHistoryRepository;
import com.example.lab1.database.repository.PlayerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
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
    Player player = PlayerRepository.findPlayer(id);
    String photo = PlayerRepository.getPhoto(player);
    List<PlayerHistory> plh = PlayerHistoryRepository.findByPlayerId(id);
    model.addAttribute("player", player);
    model.addAttribute("photo", photo);
    model.addAttribute("playerHistory", plh);
    return "player";
  }
}
