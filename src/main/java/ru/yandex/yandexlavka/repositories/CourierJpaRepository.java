package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierJpaRepository extends JpaRepository<Courier, Long> {

  // Метод был реализован нативным запросом для оптимизации
  @Query(value="SELECT * FROM courier as c ORDER BY c.id offset ?1 limit ?2", nativeQuery = true)
  public List<Courier> findAllByOffset(int offset, int limit);

  @Query("from Order o where o.courier = :courier and o.isCompleted = true and o.completedTime >= :startDate and o.completedTime < :endDate")
  List<Order> getAllCompleteOrdersDateRange(@Param("courier") Courier courier, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
