
package wad.user.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.user.entity.User;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

}
