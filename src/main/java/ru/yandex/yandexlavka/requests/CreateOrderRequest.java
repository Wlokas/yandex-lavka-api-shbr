package ru.yandex.yandexlavka.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.yandexlavka.dto.order.CreateOrderDto;

import java.util.List;

@Data
public class CreateOrderRequest {

  @Valid
  @NotNull
  private List<CreateOrderDto> orders;
}
