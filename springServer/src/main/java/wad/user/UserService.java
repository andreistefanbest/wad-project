
package wad.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import wad.user.entities.User;
import wad.user.entities.UserRepository;
import wad.utils.StringHasher;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Service()
public class UserService {

    private final UserRepository userRepository;
    private final StringHasher stringHasher;

    public UserService(UserRepository userRepository, StringHasher stringHasher) {
        this.userRepository = userRepository;
        this.stringHasher = stringHasher;
    }

    public User signIn(String name, String mail, String password) throws Exception {
        var user = new User();
        user.setFullName(name);
        user.setMail(mail);
        user.setPassword(stringHasher.getHash(password));
        user.setUserType("basic");

        return userRepository.save(user);
    }

    public User logIn(String mail, String password) throws Exception {
        for (var user : userRepository.findAll()) {
            if (matchMailAndPassword(mail, password, user)) {
                return user;
            }
        }

        return null;
    }

    private boolean matchMailAndPassword(String mail, String password, User user) throws Exception {
        return user.getMail().equals(mail) &&
                user.getPassword().equals(stringHasher.getHash(password));
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
