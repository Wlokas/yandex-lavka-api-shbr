package ru.yandex.yandexlavka.services;

import ru.yandex.yandexlavka.models.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierService {
  void addCourier(Courier courier);

  Iterable<Courier> addCouriers(List<Courier> couriersList);

  Optional<Courier> getCourier(Long id);

  List<Courier> getCouriers(Integer offset, Integer limit);
}
