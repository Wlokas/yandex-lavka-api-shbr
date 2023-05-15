package ru.yandex.yandexlavka.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.yandex.yandexlavka.dto.courier.CourierDto;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GetCouriersResponse {

  private final List<CourierDto> couriers;

  private final Integer limit;

  private final Integer offset;

}
