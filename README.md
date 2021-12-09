# User Micro Service

## BUILD & Run Locally
- mvn clean install
- mvn spring-boot:run 

## Buil, RUN Locally and Push Docker Image
- Build Docker Image
  - mvn package docker:build 
- List Docker Images
  - docker images
- Run Docker With Specific Spring Profile ( Note: We are setting which profile should be used and injecting that as enviornment Variable )
  - docker run -p 6061:6061 -e "SPRING_PROFILES_ACTIVE=prod" metamagic/user-service:1.0
- Push Docker Image to Docker Hub
  - docker image push metamagic/msk8-user-service:1.0


## API

- Welcome API
  - curl --location --request GET 'http://localhost:6061/user-service/welcome'

- Register User
  - curl --location --request POST 'http://localhost:6061/user-service/api/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginId":"ketan.gote",
    "password":"password",
    "firstName":"Ketan",
    "middleName":"D",
    "lastName":"Gote",
    "email": "ketan.gote@gmail.com",
    "age":38,
    "dob":"1983-09-09"
}'

- Authenticate User
  - curl --location --request POST 'http://localhost:6061/user-service/api/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginId":"ketan.gote",
    "password":"password"
}'

- Login User Info 
  - Send Token Which is received after Authentication
  - curl --location --request GET 'http://localhost:6061/user-service/api/v1/login/userinfo' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiNjFhZTAxODk5NTNlYTI2MTdlNWRlZGIxfGtldGFuLmdvdGVAZ21haWwuY29tIiwiaWF0IjoxNjM4OTM5Mzc1LCJleHAiOjE2Mzg5NjgxNzV9.PwDbz1R6bfhYSytaZNLnzdmIe_bI6cg4QtQf4RTK2FBAK4QBuQqpJj2mddIpm0Nw-L47NEXxwiwnBOT546j2Jg'

- List All Users
  - Send Token Which is received after Authentication
  - curl --location --request GET 'http://localhost:6061/user-service/api/v1/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZSIsImlhdCI6MTYzODUzNjE4OCwiZXhwIjoxNjM4NTY0OTg4fQ.LlAHXcmUNoEKln2lIlT-g8-dSIuRq7YRfcZxvWAfea-R39jQGSXSBDxuu4XDbPPS7meEsl_lQ4csqvTbgqFFfw'

- Find User By ID
  - Send Token Which is received after Authentication
  - curl --location --request GET 'http://localhost:6061/user-service/api/v1/61aa0648bf8a0476a991fb90' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZSIsImlhdCI6MTYzODUzNjE4OCwiZXhwIjoxNjM4NTY0OTg4fQ.LlAHXcmUNoEKln2lIlT-g8-dSIuRq7YRfcZxvWAfea-R39jQGSXSBDxuu4XDbPPS7meEsl_lQ4csqvTbgqFFfw' \

- Update User
  - Send Token Which is received after Authentication
  - curl --location --request PUT 'http://localhost:6061/user-service/api/v1/update/61aa52ccde6371720035e1bb' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZTIzNDIiLCJpYXQiOjE2Mzg1NTExNTAsImV4cCI6MTYzODU3OTk1MH0.tfm5tgkgnxpD0Lym5UuYtw-gZlgmrQ9GXzOnHNE6zwPnCGYJL46ZMW5cqpOqdRP3hlmdlWIZ0jv_1Y21XnUDOw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginId": "ketan.gote",
    "password": "password",
    "firstName": "Ketan",
    "middleName": "D",
    "lastName": "Gote",
    "email": "ketan.gote@gmail.com",
    "age": 38,
    "dob": "1983-09-09T00:00:00.000+00:00"
}'

## License  

Copyright © [MetaMagic Global Inc](http://www.metamagicglobal.com/), 2021-22.  All rights reserved.

Licensed under the Apache 2.0 License.

**Enjoy!**

