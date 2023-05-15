package ru.yandex.yandexlavka.mappers.courier;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.courier.CourierDto;
import ru.yandex.yandexlavka.dto.region.RegionDto;
import ru.yandex.yandexlavka.dto.workinghours.WorkingHoursDto;
import ru.yandex.yandexlavka.mappers.region.RegionMapper;
import ru.yandex.yandexlavka.mappers.workinghours.WorkingHoursMapper;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.Region;
import ru.yandex.yandexlavka.models.WorkingHours;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourierMapper {

  private final RegionMapper regionMapper;

  public CourierMapper(RegionMapper regionMapper) {
    this.regionMapper = regionMapper;
  }

  public Courier courierDtoToCourier(CourierDto courierDTO) {
    Courier courier = new Courier();
    courier.setId(courierDTO.getId());
    courier.setType(courierDTO.getType());

    Set<Region> regions = courierDTO.getRegions().stream()
            .map(regionMapper::regionDtoToRegion)
            .collect(Collectors.toSet());
    courier.setRegions(regions);

    Set<WorkingHours> workingHours = courierDTO.getWorkingHours().stream()
            .map(WorkingHoursMapper::workingHoursDtoToWorkingHours)
            .collect(Collectors.toSet());
    courier.setWorkingHours(workingHours);
    return courier;
  }

  public CourierDto courierToCourierDto(Courier courier) {
    CourierDto courierDTO = new CourierDto();
    courierDTO.setId(courier.getId());
    courierDTO.setType(courier.getType());

    Set<RegionDto> regionDtoList = courier.getRegions().stream()
            .map(regionMapper::regionToRegionDto)
            .collect(Collectors.toSet());
    courierDTO.setRegions(regionDtoList);

    Set<WorkingHoursDto> workingHoursDtoList = courier.getWorkingHours().stream()
            .map(WorkingHoursMapper::workingHoursToWorkingHoursDto)
            .collect(Collectors.toSet());
    courierDTO.setWorkingHours(workingHoursDtoList);
    return courierDTO;
  }

}
