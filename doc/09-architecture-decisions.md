# 09. Architecture Decisions

This chapter contains key choices and the reasoning for those choices regarding the systems design.
These choices have major impact on the application and its architecture, but are
itself influenced by technical choices, such as the choice for languages and frameworks.
It also contains technical choices and their reasoning.

## Architectural Choices

Described here are choices regarding the software architecture.
These choices may be influenced by technical choices.

### Microservice Architecture
- **Extendability**: This is achieved by using small modules with few responsibilities,
  as well as loosely coupling modules together.
- **Flexibility**: Loosely coupled modules allow for quickly interchangeable components.
- **Scalability**: Using several, small services enables horizontal scaling in necessary areas.
- **Maintainability**: Smaller, loosely coupled modules and services are easier to maintain.

### Testable Architecture
As software quality is a major requirement, ensuring constant quality is critical.

- **Dependency Injection**: Using DI allows for mocks to be inserted without resorting to mocking constructors.
  This is critical not just for clean code, but also for clean tests.

## Technical Choices

Described here are the choices regarding technical matters, such as language or framework.
As a minor warning, the answers may not be satisfactory.

### Language
As a choice between three languages, Java was the prominent choice, as I have previously worked with
spring boot, more than I can say for NodeJS and ASP.NET.

### Framework
With no real choice for java web frameworks, the only viable framework is spring boot.

### Build System
Having previously worked with gradle, albeit a long time ago, the choice was gradle.

### Database
With the choice between a myriad of databases, the choice to use an H2 in-memory database is grounded
in the tutorials I used to re-learn spring boot. They used an H2 in-memory database, so I stuck with it.

### Test Frameworks
- **Unit Testing**: The choice for JUnit with Mockito is once again grounded in the tutorials I used.
- **Integration Testing**: Once again, JUnit with Mockito and a spring boot test, as per default selection.
- **Acceptance Testing**: The choice here is the Robot Framework. Being based on python and implementing
  a human language-like abstraction layer, the framework is perfect for testing workflows without
  looking at technical details. With technical knowledge it is also greatly extensible using python modules.
- **User Interface Testing**: Using Python and Selenium, UI tests can be realized cross-platform
  and with high abstraction using multiple browsers. While this testing setup does not contain a dedicated
  testing module, Pythons `unittest` module is used for this purpose.
- **Load Testing**: Here, the Python module `Locust` is used.
  It requires a file `locustfile.py` defining the endpoints, parameters and steps.
  To execute, a webserver is started to define the server address, number of clients, request rate, etc.
  Locust is used, because it features a graphical interface, is configurable in Python and yields textual
  and graphical results regarding errors and response time.
- **Static Code Analysis**: As per lecturers requirements, SonarQube must be used.
- **Test Coverage Calculation**: Jacoco, as used in the tutorials I read.
