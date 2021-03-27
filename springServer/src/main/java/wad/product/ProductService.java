
package wad.product;

import wad.product.entity.Purchase2;
import wad.user.entity.Product;

import java.util.List;

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
