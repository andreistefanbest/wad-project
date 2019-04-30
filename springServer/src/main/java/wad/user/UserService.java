
package wad.user;

import org.springframework.stereotype.Service;
import wad.user.entities.Purchase;
import wad.user.entities.User;

/**
 *
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Service
public interface UserService {

    User signIn(String name, String mail, String password) throws Exception;
    User logIn(String mail, String password) throws Exception;
    Purchase buyPhone(Purchase purchase) throws Exception;
    User getUser(Integer id) throws Exception;
}
