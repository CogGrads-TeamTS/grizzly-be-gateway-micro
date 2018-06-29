package zuulgatewaydemo.demo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zuulgatewaydemo.demo.Model.Vendor;

import java.util.List;

@Repository
@FeignClient(name = "vendor-service", decode404 = true)
public interface VendorClient {

    @GetMapping(value = "/allByLen")
    List<Vendor> getAllProductsByLen(@RequestParam("size") int size, @RequestParam("search") String search);
}
