# Solution Strategy

## Toolchain decisions
### Language
The language of choice is Java. The reason for this choice is simply that I have never worked with C# Web Applications before and I didn't want to bother learning an entirely new web stack for this single project.

### Framework
The framework used is Spring Boot.
Spring Boot has been chosen because it is the de-facto standard for Java Web Applications, or so it seems.
Another reason is that there is some very basic experience with spring boot already present.

### Build system
The choice for Gradle as build system is similar to the choice of the framework.
With a choice between multiple build systems, primarily Maven and Gradle, the choice for Gradle comes down to
previous experience and not having to use XML for configuration.

### Static Code Analysis
The code is statically analyzed by SonarQube (sonarcloud) on every git-push event.
The choice of SonarQube is a project requirement, there is no choice.

## Library decisions
### Unittesting, Integration testing
For unittests, JUnit is used.
As an already integrated and heavily used unittesting framework, this seems like a reasonable choice.
Combined with frameworks like Mockito, pretty powerful test setups can be created.

### Load testing
For load tests, the Python-framework Locust is used.
The choice for a Python-framework is primarily ease-of-use and ease-of-development.
Load tests using Locust can be easily written and the tool features a graphical interface for variable configuration and result viewing.

## Architectural decisions
As this is a very small application with very few components, a generic class architecture without any specific pattern is chosen.
While using dependency-injection to allow for easier testing and interchangeable components was being considered, the choice was to not follow this pattern.
The reasons for this decision are:
- The application is a throw-away application, once it has been finished and graded it will never be extended and most likely deleted. Therefore, there is no requirement for dynamically changeable components.
- During testing, the same result of injecting a mock can also be achieved by exchanging an object while it is being created using PowerMockito. Or, as used in this project, mocking a static method.
