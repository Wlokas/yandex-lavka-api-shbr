package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.yandex.yandexlavka.models.Order;

import java.util.List;

public interface OrderCrudRepository extends CrudRepository<Order, Long> {
  // Метод был реализован нативным запросом для оптимизации
  @Query(value="SELECT * FROM orders as c ORDER BY c.id offset ?1 limit ?2", nativeQuery = true)
  List<Order> findAllByOffset(int offset, int limit);
}
