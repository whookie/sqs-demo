# Context and Scope

![Image](img/context.png)

## Communication: User <-> Webservice
- User requests will utilize generic HTTP parameters.
- The server will respond using JSON if the HTTP status is 200 OK.

## Communication: Webservice <-> IPAPI
- Information will be fetched using a generic GET-request with the address parameter in the URL.
- Response will always be JSON when IPAPI responds with 200 OK.
- The request may result in a 200 OK response if too many requests have been sent.
- The request will result in a 200 OK response if the address is malformed, local, reserved, etc.
  In this case, error and reason are indicated in the received JSON.

## Communication: Webservice <-> Database
- The communication between the backend and the database will use whatever spring boot magically
  decides to use by default.
