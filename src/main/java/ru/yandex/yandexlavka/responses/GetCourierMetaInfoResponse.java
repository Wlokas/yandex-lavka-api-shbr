package ru.yandex.yandexlavka.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.yandexlavka.dto.region.RegionDto;
import ru.yandex.yandexlavka.dto.workinghours.WorkingHoursDto;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.enums.CourierType;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"courier_id", "courier_type", "regions", "working_hours", "rating", "earnings"})
public class GetCourierMetaInfoResponse {
  @JsonProperty("courier_id")
  private Long courierId;

  @JsonProperty("courier_type")
  private CourierType courierType;

  private Set<RegionDto> regions;

  @JsonProperty("working_hours")
  private Set<WorkingHoursDto> workingHours;

  private Integer rating;

  private Integer earnings;
}
