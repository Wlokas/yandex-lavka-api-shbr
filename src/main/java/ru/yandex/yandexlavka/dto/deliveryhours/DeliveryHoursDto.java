package ru.yandex.yandexlavka.dto.deliveryhours;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.yandex.yandexlavka.convertors.DeliveryHoursJsonSerializer;

@Data
@JsonSerialize(using = DeliveryHoursJsonSerializer.class)
public class DeliveryHoursDto {
  private String time;

  public DeliveryHoursDto(String time) {
    this.time = time;
  }
}
