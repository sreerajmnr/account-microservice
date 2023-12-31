
set DB_URL=jdbc:h2:mem:testdb
set DB_SCHEMA=testdb
set DB_USERNAME=sa
set DB_PASSWORD=password

java -jar -Dspring.profiles.active=dev target/account-service.jar