# challenge-Vaudience-repository
project challenge for Vaudience company

I created a project with the use of the Spring.io website.
I use these dependencies :
- Spring Web, 
- Spring Data JPA
- H2 Database
- Lombok

Also, I added these dependencies to the project :
- Swagger
- Junit 5

The project has 4 main layers:
- model
- repository
- service
- rest controller


I use an in-memory H2 database. There is a class call MyRunner. it is an instance of CommandLineRunner.When the project is run, MyRunner adds a few records to the Contact table in the H2 database.

There are three main API in this project:
- API to Fetch All Contact list
- API to create a contact
- API to filter contacts by postal code

I also develop a few test classes.

URL Swagger : http://localhost:8080/swagger-ui.html

URL h2-console : http://localhost:8080/h2-console
