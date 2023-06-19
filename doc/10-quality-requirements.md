# 10. Quality Requirements

This chapter touches on the non-functional requirements, specifically regarding software quality.
It lists the key quality requirements for the application as well as their execution and impact
on the software.

## Performance
- **Response Time**:
  The response time should remain low under most conditions.
  Acceptance tests are used to ensure fast response times under ideal conditions.
  At the same time, load tests are used to ensure the response time remains fast under heavy load.
- **Throughput**:
  The application should be able to handle heavy load.
  Load tests are used to make sure the application is able to handle that.

## Reliability
- **Fault Tolerance**:
  The architecture should utilize methods to gracefully handle arising errors.
  No error shall pass silently. This is tested by ensuring a very good branch coverage in unit tests.
- **Recovery**:
  In case an uncaught error slips through the quality assurance, the system must be able to
  recover by itself. This is something spring boot does by default.

## Security
- **Input Validation**:
  All user inputs that are handled by a lower-level system must be validated once the system is entered.
  This behavior is validated using unit tests and should-fail logic.
- **Input Sanitization**:
  While the input may be valid, it may also contain unwanted spacing. Therefore, user input must be sanitized.

## Maintainability
- **Modularity**:
  The software should be built using small, decoupled modules that can be developed and tested independently.
- **Testability**:
  All code and all branches should be tested. If code is not tested, there better be a good reason for it.
- **Documentation**:
  The code should be documented. Documentation should provide reasons for a certain choice, not a description
  of the code.

## Usability
- **Consistency**:
  Visual style, behavior as well as behavioral peculiarities should match across the application.
  In other words, the usability should be consistent.
