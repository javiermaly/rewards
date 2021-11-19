# rewards

# Api-docs (Swagger)
http://localhost:8080/oa/swagger-ui.html

# Run tests
mvn package

This will package the app and run the tests.
The app is using and populating a on-memory database with random data to test proposes.

There's a Postman project in the ./dist folder with examples for the rest apis use.

# Run app
mvn clean package
java -jar target/rewards.jar

This will generate a file database located in ./data/ with with random data.

