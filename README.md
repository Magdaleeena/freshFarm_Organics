# FreshFarm Organics App 

## Set up the following and then run your SpringBoot:
1. createdb fresh_farm_organics_test
2. createdb fresh_farm_organics_development
3. Build the app and start the server, using the Maven command
```bash
mvn spring-boot:run
```
4. Run this command to seed customers and products:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=seed

```

### Login details:
Email address: test@admin.co.uk
Password: Test2025*