package ru.yandex.yandexlavka.annotation.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.exceptions.TooManyRequestsException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class ControllerRateLimitingAspect {

  @Value("${yandexlavka.ratelimiter.max_rpc_endpoint}")
  private Double maxRpcEndpoint;

  private static final Map<String, RateLimiter> limits = new ConcurrentHashMap<>();

  @Around("@annotation(ru.yandex.yandexlavka.annotation.ratelimit.RateLimited)")
  public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
    Signature signature = joinPoint.getSignature();
    Method method = signature instanceof MethodSignature ?
            ((MethodSignature) signature).getMethod() : null;
    if (method == null) {
      return joinPoint.proceed();
    }
    String key = method.toString();
    RateLimiter rateLimiter = limits.computeIfAbsent(key, k -> RateLimiter.create(maxRpcEndpoint));

    if (rateLimiter.tryAcquire()) {
      return joinPoint.proceed();
    } else {
      log.warn("[RATELIMIT] Limit reached for [{}] method", method);
      throw new TooManyRequestsException();
    }
  }

}
