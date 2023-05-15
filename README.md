# REST API сервис Яндекс Лавки
### Вступительное задание в Школу Бэкенд Разработки Яндекса 2023 г.

---

## Введение

Проект реализует базовые методы для управления заказами и курьерами, а так же RateLimit

**Требования: Java-17+, PorsgresQL**

#### Исходя из **[технического задания](doc/Product-requirements-documentation.md)** были реализованы:
- **✅ Задание 1: Базовые методы**
- **✅ Задание 2: Реализация рассчета рейтинга курьера**
- **✅ Задание 3: Реализация RateLimit на каждый EndPoint**

---

## Настройка проекта

Все основные настройки проекта находятся в **[application.properties](src/main/resources/application.properties)**
1. Настройка авторизации базы данных 
````
spring.datasource.url=jdbc:postgresql://${POSTGRES_SERVER}:${POSTGRES_PORT}/${POSTGRES_DB}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
````
2. Настройка RateLimit максимального RPS на каждый endpoint
```
yandexlavka.ratelimiter.max_rpc_endpoint=10.0
```

Упрвлением зависимостей и сборки занимается **gradle**, подробнее в файле **[build.gradle](build.gradle)**

---

## Запуск проекта

Запустить проект можно 3 способами:

1. ### Docker-compose:
``docker-compose up -d`` (вместе с PostgresQL)

``docker-compose up -d application`` (только само приложение)

2. ### Dockerfile
``docker build -t ru/yandex/yandexlavka:1.0 .``

````
docker run \
-d -p 8080:8080 \
--network="host" \
-e POSTGRES_SERVER=localhost
-e POSTGRES_PORT=5432
-e POSTGRES_DB=postgres
-e POSTGRES_USER=postgres
-e POSTGRES_PASSWORD=postgres
ru/yandex/yandexlavka:1.0
````

3. ### Gradle
Перед запуском будет необходимо установить переменные среды с данными от базы данных:
````
POSTGRES_SERVER=localhost
POSTGRES_PORT=5432
POSTGRES_DB=postgres
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
````

Или указать их в **[application.properties](src/main/resources/application.properties)**

#### Запуск
``./gradlew bootRun``

*или*
```shell
./gradlew build
java -jar build/libs/*SNAPSHOT.jar
```
---

## Документация проекта 

### [RateLimiter]() - ограничитель RPS на метод




