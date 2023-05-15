package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.yandexlavka.models.Courier;

import java.util.List;

public interface CourierJpaRepository extends JpaRepository<Courier, Long> {

  // Метод был реализован нативным запросом для оптимизации
  @Query(value="SELECT * FROM courier as c ORDER BY c.id offset ?1 limit ?2", nativeQuery = true)
  public List<Courier> findAllByOffset(int offset, int limit);
}
