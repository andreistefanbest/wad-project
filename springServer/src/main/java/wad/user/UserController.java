
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.user.entities.Purchase;
import wad.user.entities.User;

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
    
    @PutMapping(value="signIn", produces = "application/json")
    public User signIn(@RequestParam String name,
                        @RequestParam String mail,
                        @RequestParam String password) throws Exception {
        
        return userService.signIn(name, mail, password);
    }
    
    @PostMapping("logIn")
    public int logIn(@RequestParam String mail, @RequestParam String password) throws Exception {
        return userService.logIn(mail, password);
    }

    @PutMapping("buyPhone")
    public Purchase buyPhone(@RequestBody Purchase purchase) throws Exception {
        return userService.buyPhone(purchase);
    }
}
