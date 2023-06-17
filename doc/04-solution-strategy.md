# 04. Solution Strategy

Described here is the general approach and some design decisions that guide the project.
This contains architectural decisions, as well as patterns and technologies.
While some aspects of the solution strategy are predefined by the frame of the lecture,
most come naturally with the choices of technologies.

## General Achitecture
With the choice of the Spring Boot framework, a microservice architecture is the natual
choice for this project. Important here is that all components are loosely coupled.
This allows for increased scalability, portability as well as independent development of
individual components.

## Persistence
To store the custom information fields, a database is required.
Me, not knowing much about spring boot, just clicked on "H2 Database" in the spring initializr
while following a tutorial. Therefore, the data is persisted on an in-memory H2-Database.
The data models are defined using Jakarta-JPA. The choice for Jakarta-JPA over Java-JPA
is similar to the choice between databases. It simply came with the spring initializr
and a spring boot tutorial.

## External data, External API
As per lecturer-defined project requirements, access to an external API is necessary.
To fulfil this requirement, instead of storing our own geolocation information,
we completely rely on an external service. This external service is [IPAPI](ipapi.com).
Each time a user requests address information (`/information/get`), IPAPI is queried.
Then, the custom information fields are appended and the result is returned.

## Security
As the application is never to be deployed outside of my development machine, and there is no
authentication or authorization employed, a secure data transport is not necessary.
However, input validation and sanitization are in use.
All IP-Addresses are sanitized and validated.
Field names and values, due to their open and dynamic nature, are not validated or sanitized.
However, before being used in a URL or SQL query, they are escaped with the appropriate methods
to mitigate vulnerabilities such as SQL-Injection.

## Quality Assurance
With software quality being an important aspect of software development,
several measures are employed here to ensure good software quality.
This includes static code analysis, unit testing, programming interface testing,
integration testing, acceptance testing and even user interface testing.
Some tests, like unit tests, programming interface tests and integration tests,
run inside a continuous integration (CI) pipeline.
Other tests, such as user interface tests, must be run manually.
Using strong measures of quality assurance will assist in finding issues with the
software as early as possible, before correcting them becomes more expensive.
