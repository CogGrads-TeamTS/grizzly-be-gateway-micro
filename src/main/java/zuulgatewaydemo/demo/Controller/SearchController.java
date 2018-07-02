package zuulgatewaydemo.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zuulgatewaydemo.demo.Client.CategoryClient;
import zuulgatewaydemo.demo.Client.ProductClient;
import zuulgatewaydemo.demo.Client.VendorClient;
import zuulgatewaydemo.demo.Model.Category;
import zuulgatewaydemo.demo.Model.Product;
import zuulgatewaydemo.demo.Model.Vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    CategoryClient categoryClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    VendorClient vendorClient;

    @GetMapping("/search")
    public ResponseEntity<HashMap<String, List<Object>>> search(@RequestParam int size, @RequestParam String search) {

        HashMap<String, List> results = new HashMap<>();

        if (size <= 0) return new ResponseEntity("Size must be greater or equal to 0", HttpStatus.BAD_REQUEST);

        // Set remaining size
        int remSize = size;

        // Declare lists
        List<Category> categories = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Vendor> vendors = new ArrayList<>();


        // Get search list from respective microservices
        while(true) {
            products = productClient.getAllProductsByLen(remSize, search);
            remSize -= products.size(); if(remSize <= 0) break;
            
            categories = categoryClient.getAllCategoriesByLen(remSize, search);
            remSize -= categories.size(); if(remSize <= 0) break;

            vendors = vendorClient.getAllProductsByLen(remSize, search);
            break;
        }

        // Send empty array if null
        if (categories.size() == 0) categories = new ArrayList<>();
        if (products.size() == 0) products = new ArrayList<>();
        if (vendors.size() == 0) vendors = new ArrayList<>();

        results.put("categories", categories);
        results.put("products", products);
        results.put("vendors", vendors);

        return new ResponseEntity(results, HttpStatus.ACCEPTED);
    }
}