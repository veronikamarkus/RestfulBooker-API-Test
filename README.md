# API Testing with REST Assured

API Testing with REST Assured - Framework Solution for an Open API called [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html).
This repository contains a solution for API testing using REST Assured, with a custom framework built to automate and efficiently test the Restful Booker API.

The custom framework abstracts common setup tasks and provides efficient tools for interacting with the API, creating payloads, and validating responses. The tests follow business logic, ensuring clarity and maintainability.

# Prerequisites

- JDK 21
- Gradle

# Key Features
API Testing with REST Assured:

Implements tests for GET, POST, PUT, and DELETE endpoints of the Restful Booker API.
Handles common API testing scenarios like booking retrieval, booking creation, authentication, and error handling.

Custom Framework:

Built on top of REST Assured to provide reusable methods for API communication, payload creation, and response validation.
The framework abstracts common setup steps and repetitive tasks, making the tests more efficient and easier to maintain.
Test Cases:

## Some useful commands

Run all your tests:
```bash
./gradlew test
```

Reference: [Gradle Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html)
