package ru.yandex.yandexlavka.mappers.courier;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.courier.CourierDto;
import ru.yandex.yandexlavka.dto.courier.CreateCourierDto;
import ru.yandex.yandexlavka.dto.region.RegionDto;
import ru.yandex.yandexlavka.dto.workinghours.WorkingHoursDto;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateCourierDtoMapper {

  public CourierDto toCourierDTO(@NotNull CreateCourierDto createCourierDto) {
    CourierDto courierDTO = new CourierDto();
    courierDTO.setType(createCourierDto.getCourierType());

    Set<RegionDto> regionDtoList = createCourierDto.getRegions().stream()
            .map(RegionDto::new)
            .collect(Collectors.toSet());
    courierDTO.setRegions(regionDtoList);

    Set<WorkingHoursDto> workingHoursDtoList = createCourierDto.getWorkingHours().stream()
            .map(WorkingHoursDto::new)
            .collect(Collectors.toSet());
    courierDTO.setWorkingHours(workingHoursDtoList);

    return courierDTO;
  }
}
