package my.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


@RestController
public class BookServiceProviderController {
    private Logger log = LoggerFactory.getLogger(BookServiceProviderController.class);

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
        Date dt = new Date();
        log.info("id = {}, minutes = {}", id, dt.getMinutes());
        String msg = String.format("got book %d, from instance id = %s, uri = %s",
                id, registration.getInstanceId(), registration.getUri());
        if (dt.getMinutes() % 3 == 0) {
            throw new RuntimeException("wrong");
        }
        return msg;
    }
}
