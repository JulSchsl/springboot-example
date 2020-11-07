package de.schauseil.examples.springboot.greeter.internal;

import org.junit.jupiter.api.Test;

import de.schauseil.examples.springboot.greeter.api.Greeter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingServiceTest {

    private final Greeter greeter = new GreetingService();

    @Test
    void greet_WithName_ShouldReturnGreeting() {
        String name = "Foo";
        assertEquals(String.format("Hello, %s!", name), greeter.greet(name).getContent());
    }
}