package com.konrados.simpleauthbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konrados.simpleauthbackend.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiHealthTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerAndLoginFlowWorks() throws Exception {
        User user = new User("health_user", "secret");
        String payload = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(content().string("User logged successfully"));
    }

    @Test
    void questionsEndpointReturnsData() throws Exception {
        mockMvc.perform(get("/api/questions/getQuestions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].question").isNotEmpty())
                .andExpect(jsonPath("$[0].answers").isArray());
    }
}
