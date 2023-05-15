package ru.yandex.yandexlavka.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.convertors.TimeConvertor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {

  @JsonProperty("weight")
  @NotNull
  @Min(0)
  private Float weight;

  @JsonProperty("regions")
  @Min(0)
  private Integer regions;

  @JsonProperty("cost")
  @Min(0)
  private Integer cost;

  @JsonProperty("delivery_hours")
  @Size(min = 1)
  private Set<@Pattern(regexp = TimeConvertor.REGEXP_VALIDATION) String> deliveryHours;
}
