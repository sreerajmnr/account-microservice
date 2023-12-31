# Account Microservice
This is a microservice project with below features.
>**Account Operations**
>1. Create account
>2. Update account
>3. Get account
>   
>**Transacton Operations**
>1. Perform account transaction
>2. Lists account transactions (paginated)

# To build
Execute the below command from the project root folder.
```
C:\workspace\java-projects\account-service>mvn clean install
```

# To run
Execute the "run.bat" file from the project root folder.
```
C:\workspace\java-projects\account-service>run.bat
```

# Open API documention
>Open API swagger UI link is given below.
>[Account Service API Documentation](http://localhost:9090/account-service/swagger-ui/index.html)

# Postman collection for triggering the Service endpoints
Postman collection is avilable in the **postman** folder inside the porject root folder.
>1. **Postman Collection :**  ..\account-service\postman\Account_Service.postman_collection.json
>2. **Postman Environment - DEV:**  ..\account-service\postman\Dev.postman_environment.json
>3. **Postman Environment - PROD:**  ..\account-service\postman\Prod.postman_environment.json
