package zuulgatewaydemo.demo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zuulgatewaydemo.demo.Model.Category;

import java.util.List;


@Repository
@FeignClient(name = "category-service", decode404 = true)
public interface CategoryClient {

    @GetMapping(value = "/allByLen")
    List<Category> getAllCategoriesByLen(@RequestParam("size") int size, @RequestParam("search") String search);

}

