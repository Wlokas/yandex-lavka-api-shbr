FROM openjdk:17.0.1-jdk-slim AS TEMP_BUILD_IMAGE
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY . .
RUN ./gradlew build -x test --no-daemon

FROM openjdk:17.0.1-jdk-slim
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME

# установка переменных окружения
ENV POSTGRES_SERVER=localhost
ENV POSTGRES_PORT=5432
ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/*SNAPSHOT.jar yandex-lavka.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","yandex-lavka.jar"]
