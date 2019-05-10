package my;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("book-service-provider")
public interface BookClient {
    @GetMapping(value = "/books/{id}", consumes = "application/json")
    String getBook(@PathVariable("id") Integer id);

}
