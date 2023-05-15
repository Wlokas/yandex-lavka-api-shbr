package ru.yandex.yandexlavka.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Data;
import ru.yandex.yandexlavka.dto.order.CompleteOrderDto;

import java.util.List;

@Data
public class CompleteOrderRequest {

  @JsonProperty("complete_info")
  @Valid
  private List<CompleteOrderDto> completeInfo;
}
