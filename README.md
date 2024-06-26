# Twitter Clone 

## About

Spring Boot Java-based backend for Social Media App - Twitter, using Gradle, leveraging JPA for ORM and H2 as an in-memory database. Supporting core features such as posting, commenting, reactions, and comprehensive user authentication with BCrypt algorithm, ensuring robust password encoding and secure user experience.

## Tech Stack
- [Springboot - JAVA](https://docs.spring.io/spring-boot/index.html)
- [Springdata JPA](https://docs.spring.io/spring-data/jpa/reference/index.html)
- [H2](https://www.h2database.com/html/quickstart.html)
- [Springboot Gradle Plugin](https://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/html/build-tool-plugins-gradle-plugin.html)
- 
## Key Features

- Supports posting, commenting, reactions, and comprehensive user authentication
- Has an in-memory database h2 for storage
- Has BCryptEncoded user password authentication and JPA for ORM

## Getting started

### Navigating to root directory
```sh
cd /SocialMediaApp/gradle/wrapper
```

Download the ```gradle-wrapper.jar``` file in the root directory.

### Running the jar file
```sh
java -jar gradle-wrapper.jar
```
## To clone and work
```sh
git clone https://github.com/lazybug19/SocialMediaApp.git
cd SocialMediaApp
```

## To build the project
```sh
gradlew.bat build
```

## To start the application
```sh
gradlew.bat bootRun
```
