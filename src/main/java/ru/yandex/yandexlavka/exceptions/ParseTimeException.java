package ru.yandex.yandexlavka.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParseTimeException extends RuntimeException {
  public ParseTimeException(String timeString) {
    super(timeString);
  }
}
