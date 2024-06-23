package controller;

import integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ProjectControllerTest extends IntegrationTestBase {
    private final MockMvc mockMvc;
    final String name = "VSE Corporation";

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/projects/" + name))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/projects"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/projects")
                        .param("name", "test")
                        .param("date", "2001-01-01"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/projects")
                        .param("name", name)
                        .param("date", "2001-01-01"))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/" + name)
                        .param("name", name))
                .andExpect(status().is2xxSuccessful());
    }
}
