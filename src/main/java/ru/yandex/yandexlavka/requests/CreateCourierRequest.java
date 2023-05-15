package ru.yandex.yandexlavka.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.yandexlavka.dto.courier.CreateCourierDto;

import java.util.List;

@Data
public class CreateCourierRequest {

  @NotNull
  @Valid
  List<CreateCourierDto> couriers;
}
