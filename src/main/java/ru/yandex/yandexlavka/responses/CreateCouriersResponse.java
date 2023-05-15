package ru.yandex.yandexlavka.responses;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.yandexlavka.dto.courier.CourierDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateCouriersResponse {
  @NotNull
  private List<CourierDto> couriers;
}
