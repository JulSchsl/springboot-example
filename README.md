# A Spring Boot example project

An example setup for a Spring Boot webservice using ArchUnit to verify architectural contraints.

The package structure was inpired by two blog posts:

* [Package by Feature](https://phauer.com/2020/package-by-feature)
* [Clean Architecture Boundaries with Spring Boot and ArchUnit](https://reflectoring.io/java-components-clean-boundaries)

Note that ArchUnit also allows to define an Onion Architecture, which can be combined with the mechanism used here.

The annotations for the Sprint Boot tests are chosen with the goal in mind to require as few beans as possible, while still using the auto-configuration mechanism provided by Spring Boot.
