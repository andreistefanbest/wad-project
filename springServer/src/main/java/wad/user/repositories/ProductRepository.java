
package wad.user.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.user.entity.Product;

/**
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}
