package ru.yandex.yandexlavka.convertors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.yandex.yandexlavka.dto.region.RegionDto;

import java.io.IOException;

public class RegionJsonSerializer extends JsonSerializer<RegionDto> {

  @Override
  public void serialize(RegionDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeNumber(value.getRegionId());
  }
}
