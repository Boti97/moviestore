# Movie Store

Running the application:
- open project in IntelliJ
- start application (MovieStoreApplication -> main method)

Testing:
- Postman documentation with test data can be found [here](https://documenter.getpostman.com/view/8140951/UUxuipdm#f040c144-071f-4f85-ba80-87b8c1b25555).
  - Import Collection into Postman
  - Environment variables are managed automatically
    - user_id contains the id of the last logged in user (filled after calling [login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f), or [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53)) 
    - movie_id contains the last added movie (filled after calling [add movie](https://documenter.getpostman.com/view/8140951/UUxuipdm#1c97a208-487a-4bb9-aaab-905531689b5a))
    - token contains the last token (filled after calling [login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f), or [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53))
  - Endpoints available without token: [register](https://documenter.getpostman.com/view/8140951/UUxuipdm#a8941802-91b8-4246-a1d6-7f1ec1c22abf), [login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f), and [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53)
  - There is already an admin user added to the h2 database (src/main/resources/data.sql), so calling [login admin](https://documenter.getpostman.com/view/8140951/UUxuipdm#ae159939-dd2d-406e-8e0a-be4f49166e53) 
    should return with a token containing the ADMIN role. The available endpoints are: [delete movie](https://documenter.getpostman.com/view/8140951/UUxuipdm#59555c6e-a3a5-417b-8a35-ea62a192eb95), and [delete user](https://documenter.getpostman.com/view/8140951/UUxuipdm#c6f36eb7-dffa-4ac1-8610-dff75a260d55)
  - After registering a new user with [register](https://documenter.getpostman.com/view/8140951/UUxuipdm#a8941802-91b8-4246-a1d6-7f1ec1c22abf), and logging in with it ([login](https://documenter.getpostman.com/view/8140951/UUxuipdm#6162e41c-32cb-447b-8a05-fb27b12b634f)), the other endpoints will be available except [delete movie](https://documenter.getpostman.com/view/8140951/UUxuipdm#59555c6e-a3a5-417b-8a35-ea62a192eb95), and [delete user](https://documenter.getpostman.com/view/8140951/UUxuipdm#c6f36eb7-dffa-4ac1-8610-dff75a260d55).

Database:
  - H2 stores the database in the file located in: database/movie_store.mv.db
  - H2 database will be available on http://localhost:8080/h2-console after starting the application.
    - JDBC url: jdbc:h2:file:./database/movie_store
    - username: empty
    - password: empty
    
    ![Screenshot 2021-10-04 191413](https://user-images.githubusercontent.com/37210704/135895094-6d3e50f3-9fe7-4815-957f-af2f521c8502.png)

Consider application properties changes:
  - spring.jpa.hibernate.ddl-auto
    - update: default value, in our case it creates the db, but does not recreate it on restart, so the database will be intact even after restarting the application
    - create-drop: creates and drops the db schema on each restart
  - spring.sql.init.mode=always
    - on each restart inserts the data from src/main/resources/data.sql which is an admin user
    - remove after first start if it is distracting

