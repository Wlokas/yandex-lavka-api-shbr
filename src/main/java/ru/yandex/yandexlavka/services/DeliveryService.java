package ru.yandex.yandexlavka.services;

import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.GroupOrders;
import ru.yandex.yandexlavka.models.Order;

import java.util.List;

public interface DeliveryService {
  List<GroupOrders> assignOrders();
  List<GroupOrders> assignOrders(List<Order> orders, List<Courier> couriers);
}
