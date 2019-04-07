
package wad.product;

import java.util.List;
import wad.user.entities.Product;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
public interface ProductService {

    List<Product> getProducts() throws Exception;
    
}
