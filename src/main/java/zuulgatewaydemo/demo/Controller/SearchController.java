package zuulgatewaydemo.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zuulgatewaydemo.demo.Client.CategoryClient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import zuulgatewaydemo.demo.Model.Category;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    CategoryClient categoryClient;

    @GetMapping("/search")
    public Page<Category> friendlyMessage(@RequestParam int maxlen, String search, Pageable pageable){
        return categoryClient.findBySearchTerm(search, pageable);
    }
}