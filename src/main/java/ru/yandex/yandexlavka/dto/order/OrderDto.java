package ru.yandex.yandexlavka.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.yandex.yandexlavka.dto.deliveryhours.DeliveryHoursDto;
import ru.yandex.yandexlavka.dto.region.RegionDto;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDto {

  @JsonProperty("order_id")
  private Long id;

  @JsonProperty("weight")
  private Float weight;

  @JsonProperty("regions")
  private RegionDto regions;

  @JsonProperty("cost")
  private Integer cost;

  @JsonProperty("delivery_hours")
  private Set<DeliveryHoursDto> deliveryHours;

  @JsonProperty("completed_time")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private LocalDateTime completedTime;
}
