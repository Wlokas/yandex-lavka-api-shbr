package ru.yandex.yandexlavka.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.repositories.CourierCrudRepository;
import ru.yandex.yandexlavka.repositories.CourierJpaRepository;
import ru.yandex.yandexlavka.services.CourierService;

import java.util.List;
import java.util.Optional;

@Service
public class CourierServiceImpl implements CourierService {

  private final CourierCrudRepository repository;
  private final CourierJpaRepository courierJpaRepository;

  public CourierServiceImpl(CourierCrudRepository repository, CourierJpaRepository courierJpaRepository) {
    this.repository = repository;
    this.courierJpaRepository = courierJpaRepository;
  }

  @Override
  public void addCourier(Courier courier) {
    repository.save(courier);
  }

  @Override
  @Transactional
  public Iterable<Courier> addCouriers(List<Courier> couriersList) {
    return repository.saveAll(couriersList);
  }

  @Override
  public Optional<Courier> getCourier(Long id) {
    return repository.findById(id);
  }

  @Override
  public List<Courier> getCouriers(Integer offset, Integer limit) {
    return courierJpaRepository.findAllByOffset(offset, limit);
  }


}
