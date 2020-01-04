
package sw.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sw.user.entities.User;

import java.util.List;

/**
 *
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    
    @Autowired
    private UserService userService;
    

}
