# Kotlin Cart API project
This API allows the user to manage a virtual Cart through an API. The cart, if not found when adding a product, will be created to add the desired product.

The spring project comes with a seeder to seed the data on the DB when the program starts, this allows to automate the upload of products and recipes.

## Setup
1. Git clone the repository and cd into it

```
   git clone https://github.com/lucaspiritogit/kotlin-spring-api.git
   cd kotlin-spring-api
```

## Running through docker
You can run this project if you have docker pre-installed using:

```
docker compose up --build
```

This will spin up a PostgreSQL instance and the API itself

## Locally (without docker)
### Pre-requisites
- At least JDK 17
- PostgreSQL 15 running locally on port :5432, the application.properties credentials should be updated with your own.

Once inside the root folder, run:
```
./gradlew bootRun
```

## Tests

You can run
```
./gradlew test
```
This will execute the unit and integration tests. The integration tests are implemented using H2 (in-memory DB).

Following any path, the API is available at `http://localhost:8080`
I've also attached the postman collection just in case