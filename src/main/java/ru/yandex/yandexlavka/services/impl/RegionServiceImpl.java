package ru.yandex.yandexlavka.services.impl;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.models.Region;
import ru.yandex.yandexlavka.repositories.RegionRepository;
import ru.yandex.yandexlavka.services.RegionService;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RegionServiceImpl implements RegionService {
  private final RegionRepository repository;
  private final ConcurrentHashMap<Integer, Region> cacheRegion = new ConcurrentHashMap<>();

  public RegionServiceImpl(RegionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Region getRegionById(Integer id) {
    Region region = getRegionCache(id);
    if(region != null) {
      return region;
    }

    region = repository.findById(id).orElseGet(() -> createRegion(id));
    addRegionCache(region);
    return region;
  }

  private Region getRegionCache(Integer id) {
    return cacheRegion.get(id);
  }

  private void addRegionCache(Region region) {
    cacheRegion.put(region.getRegionId(), region);
  }

  @Override
  public Region createRegion(Integer id) {
    return repository.saveAndFlush(new Region(id));
  }
}
