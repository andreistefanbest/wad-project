
package wad.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.product.entities.Purchase2;
import wad.product.entities.Purchase2Repository;
import wad.user.entities.Product;
import wad.user.entities.ProductRepository;
import wad.utils.GenericService;

import static java.util.stream.Collectors.toList;

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

    @Autowired
    private GenericService genericService;

    @Override
    public List<Product> getProducts() throws Exception {
        return genericService.fetchEntities(productRepository);
    }

    @Override
    public void buy(Purchase2 purchase) throws Exception {
        purchase2Repository.save(purchase);
    }

    @Override
    public List<Purchase2> history(Integer userID) throws Exception {
        return genericService.fetchEntities(purchase2Repository)
                .stream()
                .filter(e -> e.getUserId() == userID)
                .collect(toList());
    }
}
