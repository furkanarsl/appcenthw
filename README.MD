# Appcent HW - Todo app

This project is a simple todo application API created with Spring boot framework.

## Building
To build the app locally.

``
mvn package spring-boot:repackage
``

This command will create a .jar file in the /target directory.

To run the application:

``
java -jar {JAR_NAME}.jar
``
This will run the application on the port 8080.

## Running with docker
Run
``
docker build -t tag_name
`` to build a docker image using the Dockerfile.

After the image builds: 
``
docker run -p {PORT}:8080 tag_name
``
to run the application with docker.

The project is also hosted on DockerHub

To pull the image:
``
docker pull furkanarslan/appcenthw-springboot
``

```docker run -p {PORT}:8080 furkanarslan/appcenthw-springboot```

## Docs

The OpenAPI documentation is hosted on
``
http://localhost:8080/api/docs-ui
`` and requires a registered user. To register a new user:

``
POST http://localhost:8080/api/auth/register
Body: {username:username,
password:password,
name:name}
``

and login via ``http://localhost:8080/api/auth/login Form: username=username
password=password
``