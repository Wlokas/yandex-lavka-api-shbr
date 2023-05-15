package ru.yandex.yandexlavka.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompleteOrderDto {
  @JsonProperty("courier_id")
  @NotNull
  private Long courierId;

  @JsonProperty("order_id")
  @NotNull
  private Long orderId;

  @JsonProperty("complete_time")
  @NotNull
  private LocalDateTime completeTime;
}
