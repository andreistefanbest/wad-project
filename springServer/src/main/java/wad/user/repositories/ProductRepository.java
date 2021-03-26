
package wad.user.repositories;

import org.springframework.data.repository.CrudRepository;
import wad.user.entities.Product;

/**
 *
 * @author Andrei Stefan
 * @since Apr 1, 2019
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{

}
