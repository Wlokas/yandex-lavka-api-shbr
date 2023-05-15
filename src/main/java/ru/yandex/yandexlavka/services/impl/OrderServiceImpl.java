package ru.yandex.yandexlavka.services.impl;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.models.Order;
import ru.yandex.yandexlavka.repositories.OrderCrudRepository;
import ru.yandex.yandexlavka.services.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderCrudRepository orderCrudRepository;

  public OrderServiceImpl(OrderCrudRepository orderCrudRepository) {
    this.orderCrudRepository = orderCrudRepository;
  }

  @Override
  public Optional<Order> getOrder(Long id) {
    return orderCrudRepository.findById(id);
  }

  @Override
  public Order updateOrder(Order order) {
    return orderCrudRepository.save(order);
  }

  @Override
  public List<Order> getOrders(Integer offset, Integer limit) {
    return orderCrudRepository.findAllByOffset(offset, limit);
  }

  @Override
  public Iterable<Order> addOrders(List<Order> orders) {
    return orderCrudRepository.saveAll(orders);
  }
}
