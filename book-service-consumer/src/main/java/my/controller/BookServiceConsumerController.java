package my.controller;

import my.BookServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class BookServiceConsumerController {
    private Logger log = LoggerFactory.getLogger(BookServiceConsumerController.class);

    private final String SERVICE_NAME = BookServiceClient.SERVICE_NAME;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private BookServiceClient bookClient;

    @RequestMapping("/")
    public String home() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("book-service-consumer <br>");
        sb.append("/health <br>");
        sb.append("/book <br>");
        sb.append("/book2 by feign<br>");
        sb.append("/nodes <br>");
        sb.append("/node <br>");
        return sb.toString();
    }

    @RequestMapping("/health")
    public String health() {
        return "ok";
    }

    @RequestMapping("/book")
    public String getBook() {
        return this.restTemplate.getForObject("http://" + SERVICE_NAME + "/books/1", String.class);
    }

    @RequestMapping("/book2")
    public String getBook2() {
        log.info("getBook2");
        return bookClient.getBook(2);
    }

    @RequestMapping("/nodes")
    public String getNodes() {
        List<ServiceInstance> list = discoveryClient.getInstances(SERVICE_NAME);
        List<String> uris = list.stream().map(e -> e.getUri().toString()).collect(toList());
        return Arrays.toString(uris.toArray());
    }

    @RequestMapping("/node")
    public String getNode() {
        List<ServiceInstance> list = discoveryClient.getInstances(SERVICE_NAME);
        if (list != null && list.size() > 0) {
            return list.get(0).getUri().toString();
        }
        return null;
    }
}
