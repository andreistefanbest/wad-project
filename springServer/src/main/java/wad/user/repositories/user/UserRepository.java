
package wad.user.repositories.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import wad.user.entity.User;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>, UserRepositoryCustom {

}
