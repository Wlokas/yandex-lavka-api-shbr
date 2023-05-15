package ru.yandex.yandexlavka.mappers.deliveryhours;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.convertors.TimeConvertor;
import ru.yandex.yandexlavka.dto.deliveryhours.DeliveryHoursDto;
import ru.yandex.yandexlavka.models.DeliveryHours;

import java.time.LocalTime;

@Service
public class DeliveryHoursMapper {

  public DeliveryHours deliveryHoursDtoToDeliveryHours(DeliveryHoursDto deliveryHoursDto) {
    LocalTime[] times = TimeConvertor.parseSpanTime(deliveryHoursDto.getTime());
    return new DeliveryHours(times[0], times[1]);
  }

  public DeliveryHoursDto deliveryHoursToDeliveryHoursDto(DeliveryHours deliveryHours) {
    return new DeliveryHoursDto(
            TimeConvertor.localTimesToString(
                    deliveryHours.getStartTime(),
                    deliveryHours.getEndTime()
            ));
  }

}
