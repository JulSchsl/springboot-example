package de.schauseil.examples.springboot.greeter.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.schauseil.examples.springboot.greeter.api.Greeter;
import de.schauseil.examples.springboot.greeter.api.Greeting;

@RestController
@RequiredArgsConstructor
class GreetingController {

    private final Greeter greeter;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greeter.greet(name);
    }
}
