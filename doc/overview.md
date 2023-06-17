# Project Overview

## Important Information
 - SonarQube: [Link](https://sonarcloud.io/project/overview?id=whookie_sqs-demo)
 - Documentation: `doc/`
 - Source: `src/main/`
 - Tests: `src/test/`, `testing`

## Generic Information

|||
| ---------------------- | ------------------------------ |
| Name                   | SQS Demo Application           |
| Version                | 0.0.1-SNAPSHOT                 |
|||
| Language               | Java                           |
| Documentation Language | English                        |
| Coding Language        | English                        |
| Framework              | Spring Boot                    |
| Build System           | Gradle                         |

## Testing

### Overview

| Test Type        | Language | Framework          | Automated                         |
| ---------------- | -------- | ------------------ | --------------------------------- |
| Static Analysis  |          | SonarQube          | Yes                               |
| Unit Test        | Java     | JUnit, Mockito     | Yes                               |
| Integration Test | Java     | Junit              | Yes                               |
| Acceptance Test  | Python 3 | Robot Framework    | No, Requires Server               |
| GUI Test         | Python 3 | unittest, Selenium | No, Test Requires GUI             |
| Load Test        | Python 3 | Locust             | No, Tool Requires GUI Interaction |

**Notes**
- Test coverage is generated using Jacoco with XML output

### Locations (in relation to project root)

| Type             | Location                         |
| ---------------- | -------------------------------- |
| Unit Test        | `src/test/java/com/thro/sqsdemo` |
| Integration Test | `src/test/java/com/thro/sqsdemo` |
| Acceptance Test  | `testing/acceptance_test`        |
| GUI Test         | `testing/ui_test`                |
| Load Test        | `testing/load_test`              |

**Notes**

On Information on how to run the tests inside the `testing/` subdirectory,
consult the `README.md` files found inside the respective directories.
