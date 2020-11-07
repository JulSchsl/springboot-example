package de.schauseil.examples.springboot.greeter.internal;

import org.springframework.stereotype.Service;

import de.schauseil.examples.springboot.greeter.api.Greeter;
import de.schauseil.examples.springboot.greeter.api.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@Service
class GreetingService implements Greeter {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Greeting greet(String name) {
        return new GreetingImpl(counter.incrementAndGet(), String.format(template, name));
    }
}
