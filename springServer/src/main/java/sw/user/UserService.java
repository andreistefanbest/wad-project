
package sw.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sw.user.entities.UserRepository;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final JdbcTemplate jdbcTemplate;

    public UserService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
}
