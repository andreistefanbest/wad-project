
package wad.product;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.product.entity.Purchase2;
import wad.product.entity.Purchase2Repository;
import wad.user.entity.Product;
import wad.user.repositories.ProductRepository;

import java.util.List;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Purchase2Repository purchase2Repository;

    @Override
    public List<Product> getProducts() {
        return IterableUtils.toList(productRepository.findAll());
    }

    @Override
    public void buy(Purchase2 purchase) {
        purchase2Repository.save(purchase);
    }

    @Override
    public List<Purchase2> history(Integer userID) {
        return purchase2Repository.findByUserId(userID);
    }
}
