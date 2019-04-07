
package wad.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
    
}
