package my.controller;

import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class BookServiceProviderController {
    @Resource
    private Registration registration;

    @RequestMapping("/")
    public String home() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("book-service-provider <br>");
        sb.append("/health <br>");
        sb.append("/books/{id} <br>");
        return sb.toString();
    }

    @RequestMapping("/health")
    public String health() {
        return "ok";
    }

    @RequestMapping("/books/{id}")
    public String getBook(@PathVariable Integer id) {
        String msg = String.format("got book %d, from instance id = %s, uri = %s",
                id, registration.getInstanceId(), registration.getUri());
        return msg;
    }
}
