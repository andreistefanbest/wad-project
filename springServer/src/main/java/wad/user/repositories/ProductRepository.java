
package wad.user.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import wad.user.entity.Product;

/**
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
