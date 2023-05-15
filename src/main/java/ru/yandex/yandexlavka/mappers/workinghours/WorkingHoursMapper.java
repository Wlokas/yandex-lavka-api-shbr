package ru.yandex.yandexlavka.mappers.workinghours;

import ru.yandex.yandexlavka.convertors.TimeConvertor;
import ru.yandex.yandexlavka.dto.workinghours.WorkingHoursDto;
import ru.yandex.yandexlavka.models.WorkingHours;

import java.time.LocalTime;

public class WorkingHoursMapper {

  public static WorkingHoursDto workingHoursToWorkingHoursDto(WorkingHours workingHours) {
    return new WorkingHoursDto(TimeConvertor.localTimesToString(workingHours.getStartTime(),
            workingHours.getEndTime()));
  }

  public static WorkingHours workingHoursDtoToWorkingHours(WorkingHoursDto workingHoursDto) {
    LocalTime[] times = TimeConvertor.parseSpanTime(workingHoursDto.getTime());
    return new WorkingHours(times[0], times[1]);
  }

}
