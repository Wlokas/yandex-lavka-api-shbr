package ru.yandex.yandexlavka.services.impl;

import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.GroupOrders;
import ru.yandex.yandexlavka.models.Order;
import ru.yandex.yandexlavka.services.DeliveryService;

import java.util.List;

public class DeliveryServiceImpl implements DeliveryService {
  @Override
  public List<GroupOrders> assignOrders() {
    return null;
  }

  @Override
  public List<GroupOrders> assignOrders(List<Order> orders, List<Courier> couriers) {
    return null;
  }
}
