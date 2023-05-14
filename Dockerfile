FROM openjdk:17.0.1-jdk-slim

WORKDIR /opt/app

# копируем Gradle
COPY gradlew .
COPY gradle gradle

# кэшируем зависимости
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew build --no-daemon

# копируем и компилируем исходный код
COPY src src
RUN ./gradlew build --no-daemon

# установка переменных окружения
ENV POSTGRES_SERVER=localhost
ENV POSTGRES_PORT=5432
ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

COPY build/libs/*SNAPSHOT.jar yandex-lavka.jar

ENTRYPOINT ["java","-jar","yandex-lavka.jar"]
