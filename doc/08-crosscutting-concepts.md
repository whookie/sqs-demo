# 08. Crosscutting Concepts

The concepts described here have a large impact on the design and architectural view
of the application. These concepts cut across building blocks and influence
design and implementation decisions.
The following segments describe the crosscutting concepts that influenced
the design and implementation decisions.

## Security
- **Encryption**:
  Sensitive data should be encrypted on transit.
  Authentication data, if present, should be encrypted using a salted-hash method with industry standard methods.
- **Input Validation, Sanitization**:
  User input is validated, sanitized and properly escaped. This prevents against common
  attacks against webservers, such as SQL-Injection.
- **Communication**_
  The remote API is accessed using HTTP over SSL/TLS (HTTPS).
  As the server is only ever hosted locally on the development machine, SSL/TLS is not needed.

## Resiliance
- **Exception Handing**:
  Proper try-catch blocks are used to handle exceptions.
  Exceptions must never pass silently.
- **Testing**:
  Several testing methods are employed to verify the code functions correctly.

## Performance
- **Code Optimization**:
  Refactoring should be applied to increase efficiency of a block once a block is identified as bottleneck.
- **Load Testing**:
  There should be continuous checking for critical bottlenecks using load testing. 
