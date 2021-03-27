
package wad.user.repositories.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.user.entity.User;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Repository
public interface UserRepository extends MongoRepository<User, Integer>, UserCustomRepository {

}
