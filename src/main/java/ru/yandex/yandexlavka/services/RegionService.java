package ru.yandex.yandexlavka.services;

import ru.yandex.yandexlavka.models.Region;

public interface RegionService {
  Region getRegionById(Integer id);

  Region createRegion(Integer id);
}
