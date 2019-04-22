
package wad.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.product.entities.Purchase2;
import wad.user.entities.Product;

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
