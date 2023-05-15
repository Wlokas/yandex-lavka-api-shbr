package ru.yandex.yandexlavka.convertors;

import ru.yandex.yandexlavka.exceptions.ParseTimeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Конвертор из строкового представления HH:MM в LocalTime
 */
public class TimeConvertor {

  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
  public static final String REGEXP_VALIDATION = "^(?:[01]\\d|2[0-3]):[0-5]\\d-(?:[01]\\d|2[0-3]):[0-5]\\d$";

  /**
   * Конвертирует из строки HH:MM
   * @throws ParseTimeException если неверный формат строки
   */
  public static LocalTime parseTime(String time) {
    return LocalTime.parse(time, FORMATTER);
  }

  /**
   * Конвертирует из строки HH:MM-HH:MM
   * @throws ParseTimeException если неверный формат строки
   */
  public static LocalTime[] parseSpanTime(String timeString) {
    LocalTime[] answer = new LocalTime[2];
    String[] times = timeString.split("-");

    if (times.length != 2) throw new ParseTimeException(timeString);
    if (times[0].equals(times[1])) throw new ParseTimeException(timeString);

    answer[0] = parseTime(times[0]);
    answer[1] = parseTime(times[1]);
    return answer;
  }

  public static String localTimesToString(LocalTime timeStart, LocalTime timeEnd) {
    return timeStart.format(FORMATTER) + "-" + timeEnd.format(FORMATTER);
  }
}
