# Product Micro Service

## BUILD & Run Locally
- mvn clean install
- mvn spring-boot:run 
  - This will start server on port 6062


## API

- Welcome API
  - curl --location --request GET 'http://localhost:6061/product-service/welcome'

- Add New Product
  - curl --location --request POST 'http://localhost:6062/product-service/api/v1/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiNjFhYWY2NmFjNGE5ODE2YTM2OGUyNTAyfG5haTF0aWsuZ290ZSIsImlhdCI6MTYzODU5NDE2MCwiZXhwIjoxNjM4NjIyOTYwfQ.rx_Ggv1LwKu4P4LMno4QAS3rXyBZFi0WqTkzoHVGpUuN-D12VSslFDzgjMtJvn3DkzBDFxAwD6OHAKEf68bg8A' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"IPhone 12 Pro",
    "description":"IPhone 12 Pro",
    "image":"iphone",
    "price":"79000"
}'

- List All Products
  - curl --location --request GET 'http://localhost:6062/product-service/api/v1/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiNjFhYWY2NmFjNGE5ODE2YTM2OGUyNTAyfG5haTF0aWsuZ290ZSIsImlhdCI6MTYzODU5NDE2MCwiZXhwIjoxNjM4NjIyOTYwfQ.rx_Ggv1LwKu4P4LMno4QAS3rXyBZFi0WqTkzoHVGpUuN-D12VSslFDzgjMtJvn3DkzBDFxAwD6OHAKEf68bg8A'


## License  

Copyright © [MetaMagic Global Inc](http://www.metamagicglobal.com/), 2021-22.  All rights reserved.

Licensed under the Apache 2.0 License.

**Enjoy!**

