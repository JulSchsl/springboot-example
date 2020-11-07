package de.schauseil.examples.springboot.greeter.internal;

import lombok.Value;

import de.schauseil.examples.springboot.greeter.api.Greeting;

@Value
class GreetingImpl implements Greeting {
    long id;
    String content;
}