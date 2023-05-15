package ru.yandex.yandexlavka.models;

import jakarta.persistence.*;
import lombok.*;
import ru.yandex.yandexlavka.models.enums.CourierType;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Courier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private CourierType type;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private Set<Region> regions;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "courier_id")
  private Set<WorkingHours> workingHours;


}
