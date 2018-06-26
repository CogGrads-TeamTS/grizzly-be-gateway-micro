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

        // Get search list from respective microservices
        List<Category> categories = categoryClient.getAllCategoriesByLen(size / 3, search);
        List<Product> products = productClient.getAllProductsByLen(size / 3, search);
        List<Vendor> vendors = vendorClient.getAllProductsByLen(size / 3, search);

        // Convert
        int[] sizes = calcCatSize(size);

        if (categories.size() == sizes[0]) categories = new ArrayList<>();
        if (products.size() == sizes[1]) products = new ArrayList<>();
        if (vendors.size() == sizes[2]) vendors = new ArrayList<>();

        results.put("categories", categories);
        results.put("products", products);
        results.put("vendors", vendors);

        return new ResponseEntity(results, HttpStatus.ACCEPTED);
    }

//    Helper method for dynamic search lengths that are not divisible by 3 (prevents rounding down)
    public int[] calcCatSize(int size){
        int base = size / 3;
        int[] sizes = {base, base, base};

        int rem = size % 3;
        switch (rem) {
            case 1:
                sizes[0] += 1;
                break;
            case 2:
                sizes[0] += 1;
                sizes[1] += 1;
                break;
        }
        return sizes;
    }
}