package ru.yandex.yandexlavka.mappers.order;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.deliveryhours.DeliveryHoursDto;
import ru.yandex.yandexlavka.dto.order.CreateOrderDto;
import ru.yandex.yandexlavka.dto.order.OrderDto;
import ru.yandex.yandexlavka.dto.region.RegionDto;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateOrderDtoMapper {
  public OrderDto toOrderDto(CreateOrderDto createOrderDto) {
    OrderDto orderDto = new OrderDto();
    orderDto.setCost(createOrderDto.getCost());
    orderDto.setRegions(new RegionDto(createOrderDto.getRegions()));
    orderDto.setWeight(createOrderDto.getWeight());
    Set<DeliveryHoursDto> deliveryHoursDtoSet = createOrderDto.getDeliveryHours().stream()
            .map(DeliveryHoursDto::new)
            .collect(Collectors.toSet());
    orderDto.setDeliveryHours(deliveryHoursDtoSet);
    return orderDto;
  }
}
