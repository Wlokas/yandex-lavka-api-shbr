package ru.yandex.yandexlavka.convertors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.yandex.yandexlavka.dto.deliveryhours.DeliveryHoursDto;

import java.io.IOException;

public class DeliveryHoursJsonSerializer extends JsonSerializer<DeliveryHoursDto> {
  @Override
  public void serialize(DeliveryHoursDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.getTime());
  }
}
