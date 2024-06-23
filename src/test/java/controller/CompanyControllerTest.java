package controller;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CompanyControllerTest extends IntegrationTestBase {
    private final MockMvc mockMvc;

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/companies"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/companies")
                        .param("id", "20")
                        .param("name", "TestingName"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/companies")
                .param("id", "20")
                .param("name", "TestingName"));
        mockMvc.perform(put("/companies")
                        .param("id", "20")
                        .param("name", "newTestName"))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/companies/{\\d+}"));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/1")
                        .param("id", "1"))
                .andExpect(status().is2xxSuccessful());
    }
}
