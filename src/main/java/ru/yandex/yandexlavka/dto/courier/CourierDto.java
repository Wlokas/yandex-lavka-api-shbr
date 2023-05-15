package ru.yandex.yandexlavka.dto.courier;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import ru.yandex.yandexlavka.dto.region.RegionDto;
import ru.yandex.yandexlavka.dto.workinghours.WorkingHoursDto;
import ru.yandex.yandexlavka.models.enums.CourierType;

import java.util.Set;

@Data
@JsonPropertyOrder({"courier_id", "courier_type", "regions", "working_hours"})
public class CourierDto {

  @JsonProperty("courier_id")
  private Long id;

  @JsonProperty("courier_type")
  private CourierType type;

  private Set<RegionDto> regions;

  @JsonProperty("working_hours")
  private Set<WorkingHoursDto> workingHours;

}