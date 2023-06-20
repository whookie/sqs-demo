# 11. Technical Debt

Especially when discovered late in the development lifecycle, technical debt can be really costly.
Special measures should be taken to ensure technical debt does not accumulate.
This section refers to shortcuts, workarounds and suboptimal design decisions during the development
of the application.

## Code Smells
According to SonarQube, there is a single code smell present.
This code smell is rooted in the attempt to prevent a class from being instantiated.
SonarQube does not like that, removing the private empty constructor however does lead to
code smells with higher severity.

The risk here is essentially not present.

## Design Deficiencies
Regarding modularity and scalability,  the `AddressEntry` model and the `AddressInformationBuilder`
utility class could be re-designed to allow for proper modularization.
It could also be cast into its own microservice, which would improve reusability and modularity.

## Documentation Gaps
The documentation should be mostly complete.
Gaps in the documentation are *ARC42 Chapter 7: Deployment View*.

## Unresolved Bugs
There are currently no known unresolved bugs.
