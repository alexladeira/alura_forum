package br.com.alura.alura_forum.config.auth;

import org.junit.jupiter.api.AfterEach;
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

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = {"test", "prod"})
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private URI uri;

    @Test
    void should_returnBadRequest_when_invalidLogin() throws Exception {
        String json = "{\"email\":\"invalido@email.com\", \"senha\":\"123456\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @BeforeEach
    void setUp() throws URISyntaxException {
        uri = new URI("/auth");
    }

    @AfterEach
    void tearDown() {
    }
}