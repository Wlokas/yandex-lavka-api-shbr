package ru.yandex.yandexlavka.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.CourierMetaInfo;
import ru.yandex.yandexlavka.models.Order;
import ru.yandex.yandexlavka.repositories.CourierCrudRepository;
import ru.yandex.yandexlavka.repositories.CourierJpaRepository;
import ru.yandex.yandexlavka.services.CourierService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class CourierServiceImpl implements CourierService {

  private final CourierCrudRepository repository;
  private final CourierJpaRepository courierJpaRepository;

  @Value("${yandexlavka.coefficient.earnings.foot}")
  private Integer COEFFICIENT_EARNING_FOOT;

  @Value("${yandexlavka.coefficient.earnings.bike}")
  private Integer COEFFICIENT_EARNING_BIKE;

  @Value("${yandexlavka.coefficient.earnings.auto}")
  private Integer COEFFICIENT_EARNING_AUTO;

  @Value("${yandexlavka.coefficient.rating.foot}")
  private Integer COEFFICIENT_RATING_FOOT;

  @Value("${yandexlavka.coefficient.rating.bike}")
  private Integer COEFFICIENT_RATING_BIKE;

  @Value("${yandexlavka.coefficient.rating.auto}")
  private Integer COEFFICIENT_RATING_AUTO;

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

  @Override
  public CourierMetaInfo getMetaInfoCourier(Courier courier, LocalDate startDate, LocalDate endDate) {
    List<Order> orders = courierJpaRepository.getAllCompleteOrdersDateRange(courier, startDate.atStartOfDay(), endDate.atStartOfDay());
    int rating = 0;
    int earnings = 0;

    int countOrders = orders.size();
    long countHours = ChronoUnit.HOURS.between(startDate.atStartOfDay(), endDate.atStartOfDay());

    Integer earningsCoefficient = switch (courier.getType()) {
      case FOOT -> COEFFICIENT_EARNING_FOOT;
      case BIKE -> COEFFICIENT_EARNING_BIKE;
      case AUTO -> COEFFICIENT_EARNING_AUTO;
    };

    Integer ratingCoefficient = switch (courier.getType()) {
      case FOOT -> COEFFICIENT_RATING_FOOT;
      case BIKE -> COEFFICIENT_RATING_BIKE;
      case AUTO -> COEFFICIENT_RATING_AUTO;
    };

    for(Order order : orders) {
      earnings += order.getCost() * earningsCoefficient;
    }

    rating = (int) ((countOrders) / (countHours)) * ratingCoefficient;

    return new CourierMetaInfo(courier, rating, earnings);
  }


}
