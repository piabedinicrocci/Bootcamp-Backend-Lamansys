package ar.lamansys.messages.infrastructure.input.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ar.lamansys.messages.ECommerceApp;
import ar.lamansys.messages.application.user.port.UserSessionStorage;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {
        HealthCheckController.class,
})
class HealthCheckControllerWebTest {
    private static final String HEALTH_CHECK_PATH = "/health-check";
    private static final String OTHER_VALID_PATH = "/chat/contactId";
    @MockBean
    private UserSessionStorage sessionStorage;

    @MockBean
    private ECommerceApp ECommerceApp;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheck_returnsOk() throws Exception {
        mockMvc.perform(get(HEALTH_CHECK_PATH))
            .andExpect(status().isOk());
    }

    @Test
    void otherEndpoint_returnsNotFound() throws Exception {
        mockMvc.perform(get(OTHER_VALID_PATH))
                .andExpect(status().isNotFound());

        mockMvc.perform(get(HEALTH_CHECK_PATH + "/123"))
                .andExpect(status().isNotFound());
    }

}