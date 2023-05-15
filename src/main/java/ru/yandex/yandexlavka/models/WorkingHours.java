package ru.yandex.yandexlavka.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
//@Table(indexes = @Index(name = "timeIndex", columnList = "startTime, endTime"))
public class WorkingHours {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  @Temporal(TemporalType.TIME)
  private LocalTime startTime;

  @Column(nullable = false)
  @Temporal(TemporalType.TIME)
  private LocalTime endTime;

  public WorkingHours(LocalTime startTime, LocalTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
