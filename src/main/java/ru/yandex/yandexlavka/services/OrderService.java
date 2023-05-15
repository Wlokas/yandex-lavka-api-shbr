package ru.yandex.yandexlavka.services;

import ru.yandex.yandexlavka.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

  Optional<Order> getOrder(Long id);

  List<Order> getOrders(Integer offset, Integer limit);

  Iterable<Order> addOrders(List<Order> orders);

}
