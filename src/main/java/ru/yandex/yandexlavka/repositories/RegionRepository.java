package ru.yandex.yandexlavka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.models.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
