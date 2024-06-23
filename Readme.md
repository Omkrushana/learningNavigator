# Crio Navigator API

This repository contains the Crio Navigator API, a Spring Boot application that manages students, subjects, exams, and integrates with an external Numbers API.

## Table of Contents

- [Installation](#installation)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
  - [Student Endpoints](#student-endpoints)
  - [Subject Endpoints](#subject-endpoints)
  - [Exam Endpoints](#exam-endpoints)
  - [Numbers API Endpoint](#numbers-api-endpoint)
- [Running the Application](#running-the-application)
- [Testing](#testing)

## Installation

To install and run this application locally, follow these steps:

1. Clone the repository:

    ```sh
    git clone https://github.com/your-username/crio-navigator.git
    cd crio-navigator
    ```

2. Ensure you have Java and Gradle installed on your machine.

3. Build the project:

    ```sh
    ./gradlew build
    ```

## Dependencies

This application uses the following dependencies:

- Spring Boot
- Spring Web
- Spring WebFlux
- Spring Data JPA
- H2 Database (for development and testing)
- Mockito (for testing)

## Configuration

Ensure that your `application.properties` file contains the necessary configuration for the server port and any other properties.

Example configuration (`src/main/resources/application.properties`):

```properties
spring.application.name=navigator
server.port=8080

## DB Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/navigator1
spring.datasource.username=root
spring.datasource.password=h096388@H
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

## API Endpoints
# Student Endpoints
Get all students

# GET /api/students
Response

[
  {
    "id": 1,
    "name": "John Doe",
    "age": 20,
    "email": "john.doe@example.com"
  },
  {
    "id": 2,
    "name": "Jane Doe",
    "age": 22,
    "email": "jane.doe@example.com"
  }
]

# Get student by ID
GET /api/students/{id}
Response

{
  "id": 1,
  "name": "John Doe",
  "age": 20,
  "email": "john.doe@example.com"
}

# Create a new student
POST /api/students

Request
{
  "name": "John Doe",
  "age": 20,
  "email": "john.doe@example.com"
}
Response
{
  "id": 1,
  "name": "John Doe",
  "age": 20,
  "email": "john.doe@example.com"
}

# Update a student
PUT /api/students/{id}
Request
{
  "name": "John Doe",
  "age": 21,
  "email": "john.doe@example.com"
}

Response
{
  "id": 1,
  "name": "John Doe",
  "age": 21,
  "email": "john.doe@example.com"
}

# Delete a student
DELETE /api/students/{id}
Response
{
  "status": "No Content"
}

## Subject Endpoints
# Get all subjects
GET /api/subjects

Response
[
  {
    "id": 1,
    "name": "Mathematics"
  },
  {
    "id": 2,
    "name": "Science"
  }
]

# Get subject by ID
GET /api/subjects/{id}

Response
{
  "id": 1,
  "name": "Mathematics"
}

# Create a new subject
POST /api/subjects

Request
{
  "name": "Mathematics"
}

Response
{
  "id": 1,
  "name": "Mathematics"
}

# Update a subject
PUT /api/subjects/{id}
Request
{
  "name": "Advanced Mathematics"
}
{
  "id": 1,
  "name": "Advanced Mathematics"
}

# Delete a subject
DELETE /api/subjects/{id}

Response
{
  "status": "No Content"
}

## Exam Endpoints
# Get all exams
GET /api/exams

Response:
[
  {
    "id": 1,
    "name": "Midterm Exam",
    "date": "2024-07-01",
    "subject": {
      "id": 1,
      "name": "Mathematics"
    }
  },
  {
    "id": 2,
    "name": "Final Exam",
    "date": "2024-12-01",
    "subject": {
      "id": 2,
      "name": "Science"
    }
  }
]

# Get exam by ID
GET /api/exams/{id}
Response
{
  "id": 1,
  "name": "Midterm Exam",
  "date": "2024-07-01",
  "subject": {
    "id": 1,
    "name": "Mathematics"
  }
}

# Create a new exam
POST /api/exams
Request
{
  "name": "Midterm Exam",
  "date": "2024-07-01",
  "subject": {
    "id": 1,
    "name": "Mathematics"
  }
}
Response
{
  "id": 1,
  "name": "Midterm Exam",
  "date": "2024-07-01",
  "subject": {
    "id": 1,
    "name": "Mathematics"
  }
}

# Update an exam
PUT /api/exams/{id}
Request
{
  "name": "Updated Exam",
  "date": "2024-08-01",
  "subject": {
    "id": 1,
    "name": "Mathematics"
  }
}

Response
{
  "id": 1,
  "name": "Updated Exam",
  "date": "2024-08-01",
  "subject": {
    "id": 1,
    "name": "Mathematics"
  }
}

# Delete an exam
DELETE /api/exams/{id}

Response
{
  "status": "No Content"
}

# Register a student for an exam
POST /api/exams/{examId}/students/{studentId}
Response
{
  "status": "OK"
}

# Numbers API Endpoint
GET /api/numbers/{number}
Response
"41 is the 13th smallest prime number."

## Running the Application

To run application 
./gradlew bootRun

Test
./gradlew test

