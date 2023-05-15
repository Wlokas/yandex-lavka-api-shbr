package ru.yandex.yandexlavka.dto.region;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.yandex.yandexlavka.convertors.RegionJsonSerializer;

@Data
@JsonSerialize(using = RegionJsonSerializer.class)
public class RegionDto {
  private Integer regionId;

  public RegionDto(Integer regionId) {
    this.regionId = regionId;
  }
}
