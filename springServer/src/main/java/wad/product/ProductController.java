
package wad.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.product.entity.Purchase2;
import wad.user.entity.Product;

import java.util.List;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/product/getProducts")
    public List<Product> getProducts() throws Exception {
        return productService.getProducts();
    }

    @PutMapping("/product/buy")
    public void buy(@RequestBody Purchase2 purchase) throws Exception {
        this.productService.buy(purchase);
    }

    @GetMapping("/product/history")
    public List<Purchase2> history(@RequestParam Integer userId) throws Exception {
        return productService.history(userId);
    }
}
