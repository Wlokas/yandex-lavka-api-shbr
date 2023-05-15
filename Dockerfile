FROM openjdk:17.0.1-jdk-slim AS TEMP_BUILD_IMAGE
MAINTAINER Maxim Sadowscky <sadowsckymaksim@yandex.ru>
LABEL version="1.0" description="Gradle build project yandex-lavka"
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY src $APP_HOME/src
RUN ./gradlew build -x test --no-daemon

FROM openjdk:17.0.1-jdk-slim
MAINTAINER Maxim Sadowscky <sadowsckymaksim@yandex.ru>
LABEL version="1.0" description="Start yandex-lavka project"
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
