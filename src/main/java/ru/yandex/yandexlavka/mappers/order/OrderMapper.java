package ru.yandex.yandexlavka.mappers.order;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.deliveryhours.DeliveryHoursDto;
import ru.yandex.yandexlavka.dto.order.OrderDto;
import ru.yandex.yandexlavka.mappers.deliveryhours.DeliveryHoursMapper;
import ru.yandex.yandexlavka.mappers.region.RegionMapper;
import ru.yandex.yandexlavka.models.DeliveryHours;
import ru.yandex.yandexlavka.models.Order;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
  private final RegionMapper regionMapper;
  private final DeliveryHoursMapper deliveryHoursMapper;

  public OrderMapper(RegionMapper regionMapper, DeliveryHoursMapper deliveryHoursMapper) {
    this.regionMapper = regionMapper;
    this.deliveryHoursMapper = deliveryHoursMapper;
  }

  public Order orderDtoToOrder(OrderDto orderDto) {
    Order order = new Order();
    order.setId(orderDto.getId());
    order.setCost(orderDto.getCost());
    order.setWeight(orderDto.getWeight());
    order.setRegion(regionMapper.regionDtoToRegion(orderDto.getRegions()));
    Set<DeliveryHours> deliveryHoursSet = orderDto.getDeliveryHours().stream()
            .map(deliveryHoursMapper::deliveryHoursDtoToDeliveryHours)
            .collect(Collectors.toSet());
    order.setDeliveryHours(deliveryHoursSet);
    order.setCompletedTime(orderDto.getCompletedTime());
    
    return order;
  }
  
  public OrderDto orderToOrderDto(Order order) {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(order.getId());
    orderDto.setCost(order.getCost());
    orderDto.setWeight(order.getWeight());
    orderDto.setRegions(regionMapper.regionToRegionDto(order.getRegion()));
    Set<DeliveryHoursDto> deliveryHoursSet = order.getDeliveryHours().stream()
            .map(deliveryHoursMapper::deliveryHoursToDeliveryHoursDto)
            .collect(Collectors.toSet());
    orderDto.setDeliveryHours(deliveryHoursSet);
    orderDto.setCompletedTime(order.getCompletedTime());

    return orderDto;
  }
}
