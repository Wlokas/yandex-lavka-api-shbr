package ru.yandex.yandexlavka.mappers.region;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.region.RegionDto;
import ru.yandex.yandexlavka.models.Region;
import ru.yandex.yandexlavka.services.RegionService;

@Service
public class RegionMapper {

  private final RegionService regionService;

  public RegionMapper(RegionService regionService) {
    this.regionService = regionService;
  }

  public Region regionDtoToRegion(RegionDto regionDto) {
    return regionService.getRegionById(regionDto.getRegionId());
  }

  public RegionDto regionToRegionDto(Region region) {
    return new RegionDto(region.getRegionId());
  }
}
