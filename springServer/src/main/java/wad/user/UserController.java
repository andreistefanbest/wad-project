
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.purchase.dto.NewPurchaseDTO;
import wad.user.entity.User;

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
    public User logIn(@RequestParam String mail, @RequestParam String password) throws Exception {
        return userService.logIn(mail, password);
    }

    @GetMapping("getUser")
    public User getUser(@RequestParam String id) {
        return userService.getUser(id);
    }

    @PostMapping("buyPhone")
    public String buyPhone(@RequestBody NewPurchaseDTO newPurchaseDTO) {
        return userService.buyPhone(newPurchaseDTO);
    }
}
