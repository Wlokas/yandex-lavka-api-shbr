package ru.yandex.yandexlavka.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.dto.courier.CreateCourierDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourierRequest {

  @NotNull
  @Valid
  List<CreateCourierDto> couriers;
}
