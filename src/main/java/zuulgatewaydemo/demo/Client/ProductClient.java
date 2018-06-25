package zuulgatewaydemo.demo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zuulgatewaydemo.demo.Model.Product;

import java.util.List;

@Repository
@FeignClient(name = "product-service", decode404 = true)
public interface ProductClient {

    @GetMapping(value = "/allByLen")
    List<Product> getAllProductsByLen(@RequestParam("size") int size, @RequestParam("search") String search);
}
