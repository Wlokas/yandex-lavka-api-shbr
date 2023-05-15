package ru.yandex.yandexlavka.convertors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.yandex.yandexlavka.dto.workinghours.WorkingHoursDto;

import java.io.IOException;

public class WorkingHoursJsonSerializer extends JsonSerializer<WorkingHoursDto> {
  @Override
  public void serialize(WorkingHoursDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.getTime());
  }
}
