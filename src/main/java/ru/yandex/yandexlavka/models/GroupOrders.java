package ru.yandex.yandexlavka.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class GroupOrders {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  @JoinColumn(name = "group_id")
  private Set<Order> orders;

  @ManyToOne
  @JoinColumn(name = "courier_id")
  private Courier courier;

  private LocalDate date;
}
