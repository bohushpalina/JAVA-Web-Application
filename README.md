## Лабораторная работа №2

Вторая лабораторная работа находится в ветке:

```text
lab_2
```

Цель лабораторной работы — преобразовать функциональность приложения
из лабораторной работы №1 в отдельный Web-сервис.

В данной работе используется технология **gRPC**.

Реализация выполнена на:

* Java 21;
* Spring Boot 4.1.1;
* gRPC;
* Protocol Buffers;
* JDBC;
* SQLite.

### Архитектура

В лабораторной работе №2 функциональность работы с послужным списком
хоккеистов вынесена в отдельный gRPC-сервис.

Общая схема:

```text
Клиент
   ↓
gRPC
   ↓
GrpcPlayerService
   ↓
PlayerHistoryRepository
   ↓
SQLite database.db
```

### Описание gRPC-сервиса

Интерфейс сервиса описан в файле:

```text
player.proto
```

В нём объявлен сервис:

```text
PlayerService
```

с методом:

```text
GetPlayerHistory
```

Метод принимает:

```text
HistoryRequest
```

с идентификатором игрока `playerId` и возвращает:

```text
HistoryResponse
```

с историей игрока.

Для передачи одной записи истории используется:

```text
HistoryDto
```

В него входят:

* `playerId`
* `season`
* `team`

### Реализация сервиса

gRPC-сервис реализован в классе:

```text
GrpcPlayerService
```

Класс помечен аннотацией `@GrpcService` и наследуется от
сгенерированного `PlayerServiceGrpc.PlayerServiceImplBase`. 

При получении запроса сервис:

1. получает `playerId`;
2. обращается к `PlayerHistoryRepository`;
3. получает записи истории из базы данных;
4. преобразует их в `HistoryDto`;
5. формирует `HistoryResponse`;
6. отправляет ответ клиенту.  

### Запуск

Точка входа приложения:

```text
PlayerServiceApplication.java
```

Приложение запускается через Spring Boot. 

Для генерации Java-классов из `player.proto`
используется `protobuf-maven-plugin`. 
