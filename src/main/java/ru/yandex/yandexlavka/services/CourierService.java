package ru.yandex.yandexlavka.services;

import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.CourierMetaInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourierService {
  void addCourier(Courier courier);

  Iterable<Courier> addCouriers(List<Courier> couriersList);

  Optional<Courier> getCourier(Long id);

  List<Courier> getCouriers(Integer offset, Integer limit);

  CourierMetaInfo getMetaInfoCourier(Courier courier, LocalDate startDate, LocalDate endDate);
}
