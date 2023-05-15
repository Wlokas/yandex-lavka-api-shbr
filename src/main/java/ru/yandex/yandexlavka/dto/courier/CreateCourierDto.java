package ru.yandex.yandexlavka.dto.courier;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.yandexlavka.convertors.TimeConvertor;
import ru.yandex.yandexlavka.models.enums.CourierType;

import java.util.Set;

@Data
public class CreateCourierDto {

  @NotNull
  @JsonProperty("courier_type")
  CourierType courierType;

  @NotNull
  @Size(min = 1)
  private Set<@Min(0) Integer> regions;

  @NotNull
  @JsonProperty("working_hours")
  @Size(min = 1)
  private Set<@Pattern(regexp = TimeConvertor.REGEXP_VALIDATION) String> workingHours;

}
