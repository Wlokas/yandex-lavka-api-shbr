package ru.yandex.yandexlavka;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractFileAssert;
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
import ru.yandex.yandexlavka.models.Courier;
import ru.yandex.yandexlavka.models.Region;
import ru.yandex.yandexlavka.models.enums.CourierType;
import ru.yandex.yandexlavka.repositories.CourierJpaRepository;
import ru.yandex.yandexlavka.requests.CreateCourierRequest;

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
public class CouriersCreatingTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CourierJpaRepository repository;

  @BeforeEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void createCourierTest() throws Exception {

    List<CreateCourierDto> createCourierDtoList = new ArrayList<>();
    createCourierDtoList.add(new CreateCourierDto(CourierType.AUTO, Set.of(1, 6, 10), Set.of("23:11-05:09")));
    createCourierDtoList.add(new CreateCourierDto(CourierType.FOOT, Set.of(4), Set.of("09:19-11:00", "23:11-05:09")));
    String courierJson = objectMapper.writeValueAsString(new CreateCourierRequest(createCourierDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.couriers.length()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.couriers.[0].courier_type").value("AUTO"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.couriers.[0].regions", hasItem(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.couriers.[0].regions", hasItem(6)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.couriers.[0].regions", hasItem(10)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.couriers.[0].working_hours", hasItem("23:11-05:09")));


    List<Courier> courierList = repository.findAll();
    assertThat(courierList).hasSize(2);
    assertThat(courierList.get(0).getType()).isEqualTo(CourierType.AUTO);
    assertThat(courierList.get(0).getRegions().contains(new Region(1))).isTrue();
    assertThat(courierList.get(0).getRegions().contains(new Region(6))).isTrue();
    assertThat(courierList.get(0).getRegions().contains(new Region(10))).isTrue();
  }

  @Test
  void createCourierTestWithInvalidWorkingHours() throws Exception {
    List<CreateCourierDto> createCourierDtoList = new ArrayList<>();
    createCourierDtoList.add(new CreateCourierDto(CourierType.AUTO, Set.of(1, 6, 10), Set.of("23:11-05:09")));
    createCourierDtoList.add(new CreateCourierDto(CourierType.FOOT, Set.of(4), Set.of("24:01-05:09", "23:11-05:09")));
    String courierJson = objectMapper.writeValueAsString(new CreateCourierRequest(createCourierDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    createCourierDtoList.clear();
    createCourierDtoList.add(new CreateCourierDto(CourierType.FOOT, Set.of(4), Set.of("23:01")));
    courierJson = objectMapper.writeValueAsString(new CreateCourierRequest(createCourierDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

  }

  @Test
  void createCourierTestWithInvalidType() throws Exception {
    String courierJson = "{\"couriers\":[{\"regions\":[10,1,6],\"courier_type\":\"FLY\",\"working_hours\":[\"23:11-05:09\"]}]}";

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    courierJson = "{\"couriers\":[{\"regions\":[10,1,6],\"courier_type\":\"auto\",\"working_hours\":[\"23:11-05:09\"]}]}";

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

  }

  @Test
  void createCourierTestWithoutField() throws Exception {
    List<CreateCourierDto> createCourierDtoList = new ArrayList<>();
    createCourierDtoList.add(new CreateCourierDto(null, Set.of(1, 6, 10), Set.of("23:11-05:09")));
    createCourierDtoList.add(new CreateCourierDto(CourierType.FOOT, Set.of(4), Set.of("24:01-05:09", "23:11-05:09")));
    String courierJson = objectMapper.writeValueAsString(new CreateCourierRequest(createCourierDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    createCourierDtoList.clear();
    createCourierDtoList.add(new CreateCourierDto(CourierType.FOOT, null, Set.of("23:01")));
    courierJson = objectMapper.writeValueAsString(new CreateCourierRequest(createCourierDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

    createCourierDtoList.clear();
    createCourierDtoList.add(new CreateCourierDto(CourierType.FOOT, Set.of(4), Set.of()));
    courierJson = objectMapper.writeValueAsString(new CreateCourierRequest(createCourierDtoList));

    mockMvc.perform(MockMvcRequestBuilders.post("/couriers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(courierJson))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

  }
}
