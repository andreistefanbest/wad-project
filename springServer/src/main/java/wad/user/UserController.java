
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.user.dto.PersonDTO;
import wad.user.entities.Purchase;
import wad.user.entities.User;

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
    public User getUser(@RequestParam Integer id) throws Exception {
        return userService.getUser(id);
    }

    @PutMapping("buyPhone")
    public Purchase buyPhone(@RequestBody Purchase purchase) throws Exception {
        return userService.buyPhone(purchase);
    }

    @GetMapping("purchases")
    public List<Purchase> getPurchases(@RequestParam Integer userId) throws Exception {
        return userService.getPurchases(userId);
    }

    @GetMapping("persons")
    public List<PersonDTO> getPersons() throws Exception {
        return userService.fetchPersons();
    }
}
