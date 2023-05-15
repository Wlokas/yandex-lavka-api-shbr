package ru.yandex.yandexlavka.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourierMetaInfo {
  private Courier courier;
  private Integer rating;
  private Integer earnings;
}
