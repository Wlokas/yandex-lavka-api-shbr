package ru.yandex.yandexlavka.controllers;

import com.google.common.collect.Lists;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.annotation.ratelimit.RateLimited;
import ru.yandex.yandexlavka.dto.order.OrderDto;
import ru.yandex.yandexlavka.exceptions.IllegalLimitArgumentException;
import ru.yandex.yandexlavka.exceptions.IllegalOffsetArgumentException;
import ru.yandex.yandexlavka.exceptions.OrderNotFoundException;
import ru.yandex.yandexlavka.mappers.order.CreateOrderDtoMapper;
import ru.yandex.yandexlavka.mappers.order.OrderMapper;
import ru.yandex.yandexlavka.models.Order;
import ru.yandex.yandexlavka.requests.CreateOrderRequest;
import ru.yandex.yandexlavka.services.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderMapper orderMapper;
  private final CreateOrderDtoMapper createOrderDtoMapper;
  private final OrderService orderService;

  public OrderController(OrderMapper orderMapper, CreateOrderDtoMapper createOrderDtoMapper, OrderService orderService) {
    this.orderMapper = orderMapper;
    this.createOrderDtoMapper = createOrderDtoMapper;
    this.orderService = orderService;
  }

  @GetMapping("/complete")
  @RateLimited
  public ResponseEntity<?> completedOrders() {
    return ResponseEntity.ok("ok");
  }

  @PostMapping("/assign")
  @RateLimited
  public ResponseEntity<?> assignOrders(@RequestParam LocalDate date) {
    return ResponseEntity.ok("ok");
  }

  @GetMapping
  @RateLimited
  public ResponseEntity<?> getOrders(@RequestParam(value = "limit") Optional<Integer> limitOptional,
                                       @RequestParam(value = "offset") Optional<Integer> offsetOptional) {
    Integer limit = limitOptional.orElse(1);
    Integer offset = offsetOptional.orElse(0);

    if(limit < 1) throw new IllegalLimitArgumentException();
    if(offset < 0) throw new IllegalOffsetArgumentException();

    List<OrderDto> orders = orderService.getOrders(offset, limit).stream()
            .map(orderMapper::orderToOrderDto)
            .toList();

    return ResponseEntity.ok(orders);
  }

  @GetMapping("/{order_id}")
  @RateLimited
  public ResponseEntity<?> getOrder(@PathVariable Long order_id) {
    Order order = orderService.getOrder(order_id).orElseThrow(OrderNotFoundException::new);

    return ResponseEntity.ok(orderMapper.orderToOrderDto(order));
  }

  @PostMapping
  @RateLimited
  public ResponseEntity<?> createOrders(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
    List<Order> orders = createOrderRequest.getOrders().stream()
            .map(createOrderDtoMapper::toOrderDto)
            .map(orderMapper::orderDtoToOrder)
            .toList();
    List<OrderDto> ordersCreate = Lists.newArrayList(orderService.addOrders(orders)).stream()
            .map(orderMapper::orderToOrderDto)
            .toList();

    return ResponseEntity.ok(ordersCreate);
  }
}
