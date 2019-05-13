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

java -jar target/xxx.jar
```

## 3. do not register itself as service
```
消费者不对外提供服务不需要在注册中心注册为服务
```

## 4. feign
```
@FeignClient
```

## 5. hystrix
```
通常是由服务的消费者负责限流熔断，保护服务
增加一层，服务的代理，负责限流熔断，并对外提供服务。消费者通过代理间接访问服务
```