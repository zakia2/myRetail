The database for myRetail application is hosted on Amazon DynamoDB.

In order to test the REST endpoints after running the jar application locally:

1. Testing the GET /products/{id} endpoint: Use curl command

   curl -X GET http://localhost:8080/products/13860428
   
2.  Testing the GET /products/info/{id} endpoint: Use curl command  
   
    curl -X GET http://localhost:8080/products/info/13860428
    
3.  Testing the PUT /products/{id} endpoint: Use curl command   
   
    curl -H "Content-Type: application/json" -X PUT -d '{"product_id":"13860428","price":450.0,"currency":"USD"}' http://localhost:8080/products/13860428

    