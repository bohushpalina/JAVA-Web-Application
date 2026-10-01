package com.example.playerservice.service;

import com.example.playerservice.database.model.PlayerHistory;
import com.example.playerservice.database.repository.PlayerHistoryRepository;
import com.example.playerservice.grpc.HistoryDto;
import com.example.playerservice.grpc.HistoryRequest;
import com.example.playerservice.grpc.HistoryResponse;
import com.example.playerservice.grpc.PlayerServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

// 1. Эта аннотация говорит Spring Boot: "Сделай этот класс доступным по сети через gRPC"
@GrpcService
public class GrpcPlayerService extends PlayerServiceGrpc.PlayerServiceImplBase {

  // 2. Переопределяем метод, который мы описали в файле player.proto
  @Override
  public void getPlayerHistory(HistoryRequest request, StreamObserver<HistoryResponse> responseObserver) {

    // 3. Достаем ID игрока из пришедшего сетевого запроса
    String playerId = request.getPlayerId();

    // 4. Идем в базу данных (ВНИМАНИЕ: вызови тут свой метод из PlayerHistoryRepository)
    // Я написала getPlayerHistory, но если он у тебя называется иначе — поменяй название
    List<PlayerHistory> dbHistories = PlayerHistoryRepository.findByPlayerId(playerId);

    // 5. Создаем "строитель" для нашего ответа
    HistoryResponse.Builder responseBuilder = HistoryResponse.newBuilder();

    // 6. Перекладываем данные из твоей БД в формат gRPC (Dto)
    for (PlayerHistory dbHistory : dbHistories) {
      HistoryDto dto = HistoryDto.newBuilder()
              .setPlayerId(dbHistory.getPlayerid())
              .setSeason(dbHistory.getSeason())
              // Если в таблице есть еще поля, добавь их сюда через .set...
              .build();

      responseBuilder.addHistory(dto);
    }

    // 7. Отправляем готовый ответ клиенту
    responseObserver.onNext(responseBuilder.build());

    // 8. Говорим, что передача данных завершена
    responseObserver.onCompleted();
  }
}