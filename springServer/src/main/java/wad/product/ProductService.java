
package wad.product;

import java.util.List;

import wad.product.entities.Purchase2;
import wad.user.entities.Product;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
public interface ProductService {

    List<Product> getProducts() throws Exception;
    void buy(Purchase2 purchase) throws Exception;
    List<Purchase2> history(Integer userID) throws Exception;
    
}
