package de.schauseil.examples.springboot.greeter.internal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = GreetingController.class)
@ComponentScan
@AutoConfigureMockMvc
public class BeanCounter {

    private final ApplicationContext context;

    public BeanCounter(ApplicationContext context) {
        this.context = context;
    }

    @Test
    void printBeanCount() {
        System.out.println("Context has beans of count " + context.getBeanDefinitionNames().length);
    }
}
