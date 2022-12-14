## JWT Tester

A Burpsuite extension that can test for JWT based security vulnerabilities.

### Supported vulnerabilities

1. `NONE` algorithm ([reference](https://auth0.com/blog/critical-vulnerabilities-in-json-web-token-libraries/#Meet-the--None--Algorithm))

### Features

1. Fast (by using threads to execute multiple requests concurrently)

### Installation

Requirements:

1. Burpsuite (Pro or Community)
2. JDK 17+

Steps:

1. `git clone`
2. `cd jwt-tester`
3. `./gradlew build`
4. Add the `.jar` file (located in `./lib/build/libs`) to burp extensions.
