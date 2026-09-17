# Hockey Players Web Application
Веб-приложение на Java для работы с базой данных хоккеистов.

## Стек

- Java 21
- Spring Boot 4.1.1
- Spring MVC
- Thymeleaf
- JDBC
- SQLite
- SQLite JDBC Driver 3.53.2.1

## База данных
Используется SQLite-база:
```text
src/main/resources/database.db
````

Основные таблицы:
* `Roster` — основная информация о хоккеистах
* `PlayerHistory` — послужной список хоккеистов

Фотографии хранятся в:
```text
src/main/resources/static/images/
```

## Структура проекта

```text
src/main/java/com/example/lab1/

├── DemoApplication.java
│
├── controller/
│   └── MainController.java
│
└── database/
    ├── DatabaseConnection.java
    │
    ├── model/
    │   ├── Player.java
    │   └── PlayerHistory.java
    │
    └── repository/
        ├── PlayerRepository.java
        └── PlayerHistoryRepository.java
```

HTML-шаблоны:
```text
src/main/resources/templates/

├── index.html
└── player.html
```

## Основные возможности

### Главная страница
```text
GET /
```
Отображает список хоккеистов.
Поддерживается фильтрация по:
* позиции
* году рождения от/до
* весу от/до
* росту от/до
Фильтры являются необязательными и могут использоваться независимо друг от друга.
Для фильтрации используется `PreparedStatement`.

### Страница игрока
```text
GET /player/{id}
```

Отображает:
* личные данные хоккеиста
* фотографию
* послужной список
Послужной список выбирается из таблицы `PlayerHistory` по `playerid`.

## Работа с БД

Основные методы `PlayerRepository`:

```text
findAll()
getUniquePositions()
findByFilters(...)
findPlayer(...)
getPhoto(...)
```

`PlayerHistoryRepository`:

```text
findByPlayerId(...)
```

Общий принцип работы:

```text
Браузер
   ↓
Controller
   ↓
Repository
   ↓
JDBC
   ↓
SQLite database.db
```

## Запуск
Запустить:

```text
DemoApplication.java
```

После запуска приложение доступно по адресу:
```text
http://localhost:8080/
```
