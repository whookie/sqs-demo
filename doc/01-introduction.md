# 01. Introduction and Goals

This project serves as my examination project for the university subject of Software Quality Assurance.
The goal is to show the usage of several types of quality assurance in software systems,
including but not limited to unit testing, Integration testing, Acceptance testing, Documentation, etc.

The primary project requirements are:
 - The software must offer a REST-Service
 - The software must utilize an external REST-Service
 - The software must access a database
 - The software must be written in Java, C# or NodeJS

In consideration of the requirements, the project presented here will offer a REST-Service
to extend metadata about IP-Addresses, gathered using [IPAPI](IPAPI.co), with custom information fields.
In short, adding custom information to WHOIS entries.

To achieve this, the following requirements must be met:
 - The API must offer an endpoint to create custom information fields, bound to an IP address.
 - The API must offer an endpoint to delete custom information fields.
 - The API must offer an endpoint to get all created fields associated with an IP address.
 - The response time of all API calls must be below 500ms, without considering round-trip time.

## Use Cases

| Use Case No.  | 1                                                                     |
| ------------- | --------------------------------------------------------------------- |
| Name          | The user adds a new information field                                 |
| Precondition  | None                                                                  |
| Scenario      | The user POSTs to /information/add                                    |
| Postcondition | The database should contain the added information field               |

| Use Case No.  | 2                                                                     |
| ------------- | --------------------------------------------------------------------- |
| Name          | The user wishes to delete a previously added information field        |
| Precondition  | Information field added, see Use Case No. 1                           |
| Scenario      | The user sends a DELETE to /information/delete                        |
| Postcondition | The database should not contain the deleted information field anymore |

| Use Case No.  | 3                                                                     |
| ------------- | --------------------------------------------------------------------- |
| Name          | The user requests additional information on an IP address             |
| Precondition  | Information field added, see Use Case No. 1                           |
| Scenario      | The user sends a GET to /information/get                              |
| Postcondition | The IP-Geolocation response is returned, with the added information   |

## Primary Quality Goals

| Name     | Reliability                    |
| -------- | ------------------------------ |
| Priority | 1                              |
| Reason   | The API must respond correctly |
| Method   | Good general test coverage     |
| Gates    | Line Coverage > 90%            |
|          | Branch coverage > 90%          |

| Name     | Usability                                    |
| -------- | -------------------------------------------- |
| Priority | 2                                            |
| Reason   | The API must be easy to use                  |
| Method   | UI Tests, Integration Tests, Usability Tests |
| Gates    | Functional, intuitive User Interface         |
|          | Responsive API Endpoints                     |

| Name     | Performance Efficiency                                 |
| -------- | ------------------------------------------------------ |
| Priority | 3                                                      |
| Reason   | The API should not be the reason for a user-side delay |
| Method   | The API must respond quickly                           |
| Gates    | Response Time < 500ms, on all endpoints                |

## Stakeholders

| Role          | Expectation                                               |
| ------------- | --------------------------------------------------------- |
| Developer     | The system should be easy to extend and well documented   |
| Administrator | The system should be easy to install, maintain and update |
| CFO           | The running system should produce little cost             |
| User          | The system should be well documented and easy to use      |
| Me            | The system should give me a good grade                    |
