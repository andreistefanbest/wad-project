
package wad.user;

import org.springframework.web.bind.annotation.*;
import wad.user.entities.User;

/**
 *
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "signIn", produces = "application/json")
    public User signIn(@RequestParam String name,
                       @RequestParam String mail,
                       @RequestParam String password) throws Exception {

        return userService.signIn(name, mail, password);
    }

    @PostMapping("logIn")
    public User logIn(@RequestParam String mail,
                      @RequestParam String password) throws Exception {
        return userService.logIn(mail, password);
    }

    @GetMapping("getUser")
    public User getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }
}
