package de.schauseil.examples.springboot.greeter.internal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import de.schauseil.examples.springboot.greeter.api.Greeter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GreetingControllerTest.class) // use only the reduced context defined by this class
@ComponentScan // scan components in the package of this class
@AutoConfigureMockMvc
class GreetingControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private Greeter mockGreeter;

    @Autowired
    GreetingControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getGreeting_withNoParams_shouldReturn200() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk());
    }

    @Test
    void getGreeting_withNoParams_shouldCallGreeterServiceWithDefaultParam() throws Exception {
        mockMvc.perform(get("/greeting"));
        Mockito.verify(mockGreeter, Mockito.times(1)).greet("World");
    }

    @Test
    void getGreeting_withParam_shouldCallGreeterServiceWithParam() throws Exception {
        mockMvc.perform(get("/greeting?name=UnitTest"));
        Mockito.verify(mockGreeter, Mockito.times(1)).greet("UnitTest");
    }
}