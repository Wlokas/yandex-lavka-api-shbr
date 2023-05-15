# RateLimit

### Ограничивает кол-во вызовов метода в секунду *(RPS)*.

В реализации использует RateLimiter от Google Guava

В случае преодоление ограничения выбрасывается исключение [TooManyRequestsException](/src/main/java/ru/yandex/yandexlavka/exceptions/TooManyRequestsException.java)

Настраивается RPS настройкой в application.properties
``yandexlavka.ratelimiter.max_rpc_endpoint=10.0``

Для активации RateLimit необходимо над методом поставить аннотацию
``@RateLimited``

Основной обработчик аннотации находится в [annotation.ratelimit.ControllerRateLimitingAspect](/src/main/java/ru/yandex/yandexlavka/annotation/ratelimit/ControllerRateLimitingAspect.java)