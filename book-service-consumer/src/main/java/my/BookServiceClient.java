package my;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = BookServiceClient.BOOK, fallback = BookClientFallback.class)
@FeignClient(value = BookServiceClient.SERVICE_NAME, fallbackFactory = BookClientFallbackFactory.class)
public interface BookServiceClient {
    String SERVICE_NAME = "book-service-provider";

    @GetMapping(value = "/books/{id}", consumes = "application/json")
    String getBook(@PathVariable("id") Integer id);
}

@Component
class BookClientFallback implements BookServiceClient {
    @Override
    public String getBook(Integer id) {
        return "service is busy";
    }
}

@Component
class BookClientFallbackFactory implements FallbackFactory<BookServiceClient> {
    @Override
    public BookServiceClient create(Throwable cause) {
        return new BookServiceClient() {
            @Override
            public String getBook(Integer id) {
                return "service is busy " + cause.getMessage();
            }
        };
    }
}