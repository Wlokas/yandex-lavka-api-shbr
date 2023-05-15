package ru.yandex.yandexlavka;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.yandex.yandexlavka.dto.courier.CreateCourierDto;
import ru.yandex.yandexlavka.dto.order.CreateOrderDto;
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.Region;
import ru.yandex.yandexlavka.models.enums.CourierType;
import ru.yandex.yandexlavka.repositories.CourierJpaRepository;
import ru.yandex.yandexlavka.repositories.OrderCrudRepository;
import ru.yandex.yandexlavka.requests.CreateCourierRequest;
import ru.yandex.yandexlavka.requests.CreateOrderRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class OrdersCreatingTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private OrderCrudRepository repository;

  @BeforeEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void createOrderTest() throws Exception {

    List<CreateOrderDto> createOrderDtoList = new ArrayList<>();
    createOrderDtoList.add(new CreateOrderDto(10.0f, 5, 100, Set.of("23:15-14:15", "03:15-11:00")));
    createOrderDtoList.add(new CreateOrderDto(500.0f, 1, 1509, Set.of("17:00-17:01", "03:15-11:00")));
    createOrderDtoList.add(new CreateOrderDto(10.0f, 4, 1234, Set.of("09:00-15:00")));
    String courierJson = objectMapper.writeValueAsString(new CreateOrderRequest(createOrderDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

  }
}
