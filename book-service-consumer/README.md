# how to run

## 1. start consul
```
for dev environment

consul agent -dev

consul will run at 8500 port
http://localhost:8500

https://www.consul.io/
```

## 2. start service

```
mvn clean package
mvn spring-boot:run

java -jar consul-client-1.0.jar
```