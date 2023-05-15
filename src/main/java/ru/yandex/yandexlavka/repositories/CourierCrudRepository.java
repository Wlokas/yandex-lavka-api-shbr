package ru.yandex.yandexlavka.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.yandexlavka.models.Courier;

public interface CourierCrudRepository extends CrudRepository<Courier, Long> {
}
