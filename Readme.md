**To build:**

    mvn clean package

**To run:**

    java -jar target/resilience-demo-0.0.1-SNAPSHOT.jar


**To test:**

View the following 3 endpoints in a web browser:

* http://localhost:8080/success (successful call)


* http://localhost:8080/exception-naive (call that throws an exception but does not have a circuit breaker. It will route through the exception handler)


* http://localhost:8080/exception-with-circuit-breaker (call that throws an exception but is caught by the circtuit breaker, has retries and a fallback. This one avoids the global exception handler)