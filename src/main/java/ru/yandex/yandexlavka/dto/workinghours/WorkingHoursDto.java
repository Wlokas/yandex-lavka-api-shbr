package ru.yandex.yandexlavka.dto.workinghours;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.yandex.yandexlavka.convertors.WorkingHoursJsonSerializer;

@Data
@JsonSerialize(using = WorkingHoursJsonSerializer.class)
public class WorkingHoursDto {

  private String time;

  public WorkingHoursDto(String time) {
    this.time = time;
  }
}
