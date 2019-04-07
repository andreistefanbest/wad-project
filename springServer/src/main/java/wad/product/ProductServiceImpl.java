
package wad.product;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.user.entities.Product;
import wad.user.entities.ProductRepository;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() throws Exception {
        List<Product> result = new ArrayList<>();
        
        productRepository.findAll().forEach(result::add);
        
        return result;
    }
    
}
