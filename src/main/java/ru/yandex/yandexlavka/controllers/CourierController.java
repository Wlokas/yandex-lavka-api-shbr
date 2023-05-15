package ru.yandex.yandexlavka.controllers;

import com.google.common.collect.Lists;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.annotation.ratelimit.RateLimited;
import ru.yandex.yandexlavka.dto.courier.CourierDto;
import ru.yandex.yandexlavka.exceptions.CourierNotFoundException;
import ru.yandex.yandexlavka.exceptions.IllegalLimitArgumentException;
import ru.yandex.yandexlavka.exceptions.IllegalOffsetArgumentException;
import ru.yandex.yandexlavka.mappers.courier.CourierMapper;
import ru.yandex.yandexlavka.mappers.courier.CreateCourierDtoMapper;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.requests.CreateCourierRequest;
import ru.yandex.yandexlavka.responses.CreateCouriersResponse;
import ru.yandex.yandexlavka.responses.GetCouriersResponse;
import ru.yandex.yandexlavka.services.CourierService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/couriers")
@Slf4j
public class CourierController {

  private final CourierService courierService;
  private final CourierMapper courierMapper;
  private final CreateCourierDtoMapper createCourierDtoMapper;

  public CourierController(CourierService courierService, CourierMapper courierMapper, CreateCourierDtoMapper createCourierDtoMapper) {
    this.courierService = courierService;
    this.courierMapper = courierMapper;
    this.createCourierDtoMapper = createCourierDtoMapper;
  }


  @GetMapping
  @RateLimited
  public ResponseEntity<?> getCouriers(@RequestParam(value = "limit") Optional<Integer> limitOptional,
                                       @RequestParam(value = "offset") Optional<Integer> offsetOptional) {
    Integer limit = limitOptional.orElse(1);
    Integer offset = offsetOptional.orElse(0);

    if(limit < 1) throw new IllegalLimitArgumentException();
    if(offset < 0) throw new IllegalOffsetArgumentException();

    return new ResponseEntity<>(
            new GetCouriersResponse(courierService.getCouriers(offset, limit).stream()
                    .map(courierMapper::courierToCourierDto)
                    .toList(), limit, offset), HttpStatus.OK);
  }

  @GetMapping("/{courier_id}")
  @RateLimited
  public ResponseEntity<?> getCourier(@PathVariable Long courier_id) {
    Courier courier = courierService.getCourier(courier_id).orElseThrow(CourierNotFoundException::new);

    return ResponseEntity.ok(courierMapper.courierToCourierDto(courier));
  }

  @PostMapping
  @RateLimited
  public ResponseEntity<?> createCouriers(@Valid @RequestBody CreateCourierRequest request) {
    List<Courier> courierList = request.getCouriers().stream()
            .map(createCourierDtoMapper::toCourierDTO)
            .map(courierMapper::courierDtoToCourier)
            .toList();
    List<CourierDto> createdList = Lists.newArrayList(courierService.addCouriers(courierList))
            .stream()
            .map(courierMapper::courierToCourierDto)
            .toList();
    return ResponseEntity.ok(new CreateCouriersResponse(createdList));
  }

}
