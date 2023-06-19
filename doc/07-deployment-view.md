# 07. Deployment View

The application is deployed by cloning the git repository and starting the application with `./gradlew start`.
I assume spring boot offers some options to choose databases, add load balancers or add buffers and caches,
however all that is not used here.
Just a simple and plain gradle application.
Scaling is not relevant, as (hopefully) no one will ever really use this project.
