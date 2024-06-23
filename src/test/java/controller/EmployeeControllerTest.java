package controller;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class EmployeeControllerTest extends IntegrationTestBase {
    private final MockMvc mockMvc;
    final UUID uuid = fromString("563dbd52-0f82-4fd5-b74d-8ac2c13e881c");

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/employees/" + uuid))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().is2xxSuccessful());
    }

    @Transactional
    @Test
    void create() throws Exception {
        UUID uuids = randomUUID();
        mockMvc.perform(post("/employees")
                        .param("id", String.valueOf(uuids))
                        .param("firstName", "TestingName")
                        .param("lastName", "TestingName")
                        .param("role", "Test")
                        .param("email", "Test")
                        .param("companyId", "1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/employees")
                        .param("id", uuid.toString())
                        .param("firstName", "TestingName")
                        .param("lastName", "TestingName")
                        .param("role", "Test")
                        .param("email", "Test")
                        .param("companyId", "1"))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/employees/{\\d+}"));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/" + uuid)
                        .param("id", uuid.toString()))
                .andExpect(status().is2xxSuccessful());
    }
}
