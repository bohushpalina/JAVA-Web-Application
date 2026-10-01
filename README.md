## Лабораторная работа №1

Первая лабораторная работа находится в отдельной ветке:

```text
lab_1
````

### Hockey Players Web Application

Веб-приложение на Java для работы с базой данных хоккеистов.

### Стек

* Java 21
* Spring Boot 4.1.1
* Spring MVC
* Thymeleaf
* JDBC
* SQLite
* SQLite JDBC Driver 3.53.2.1

### База данных

Используется SQLite-база:

```text
src/main/resources/database.db
```

Основные таблицы:

* `Roster` — основная информация о хоккеистах
* `PlayerHistory` — послужной список хоккеистов

Фотографии хранятся в:

```text
src/main/resources/static/images/
```

### Основные возможности

Главная страница:

```text
GET /
```

Отображает список хоккеистов и поддерживает фильтрацию по:

* позиции;
* году рождения от/до;
* весу от/до;
* росту от/до.

Фильтры являются необязательными и могут использоваться независимо друг от друга. Для фильтрации используется `PreparedStatement`.

Страница игрока:

```text
GET /player/{id}
```

Отображает:

* личные данные хоккеиста;
* фотографию;
* послужной список.

Послужной список выбирается из таблицы `PlayerHistory` по `playerid`.

### Работа с БД

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

Общая схема работы:

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

### Запуск

Запустить:

```text
DemoApplication.java
```

После запуска приложение доступно по адресу:

```text
http://localhost:8080/
```


