# Movie Store

Running the application:
- open project in IntelliJ
- start application (MovieStoreApplication -> main method)

Testing:
- Postman documentation with test data can be found [here](https://documenter.getpostman.com/view/8140951/UUxuipdm#f040c144-071f-4f85-ba80-87b8c1b25555).
  - Import Collection into Postman
  - Environment variable are managed automatically
    - user_id contains the id of the last logged in user (filled after calling [login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f), or [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53)) 
    - movie_id contains the last added movie (filled after calling [add movie](https://documenter.getpostman.com/view/8140951/UUxuipdm#1c97a208-487a-4bb9-aaab-905531689b5a))
    - token contains the last token (filled after calling [login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f), or [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53))
  - Endpoint available without token: [register](https://documenter.getpostman.com/view/8140951/UUxuipdm#a8941802-91b8-4246-a1d6-7f1ec1c22abf), [login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f), and [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53)
  - There is already an admin user added to the h2 database (src/main/resources/data.sql), so calling [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53) 
    should return with a token containing the ADMIN role. The available endpoints are: [delete movie](https://documenter.getpostman.com/view/8140951/UUxuipdm#59555c6e-a3a5-417b-8a35-ea62a192eb95), and [delete user](https://documenter.getpostman.com/view/8140951/UUxuipdm#c6f36eb7-dffa-4ac1-8610-dff75a260d55)
  - After registering a new user with [register](https://documenter.getpostman.com/view/8140951/UUxuipdm#a8941802-91b8-4246-a1d6-7f1ec1c22abf), and logging in with it ([login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f)), the other endpoint will be available except [delete movie](https://documenter.getpostman.com/view/8140951/UUxuipdm#59555c6e-a3a5-417b-8a35-ea62a192eb95), and [delete user](https://documenter.getpostman.com/view/8140951/UUxuipdm#c6f36eb7-dffa-4ac1-8610-dff75a260d55).

Database:
  - H2 stores the database in the file locating in: database/movie_store.mv.db
  - H2 database will be available on http://localhost:8080/h2-console after starting the application.
    - JDBC url: jdbc:h2:file:./database/movie_store
    - username: empty
    - password: empty
    