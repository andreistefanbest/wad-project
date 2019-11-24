
package sw.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sw.user.dto.JobDTO;
import sw.user.dto.PersonDTO;
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

    @GetMapping("persons")
    public List<PersonDTO> getPersons() throws Exception {
        return userService.fetchPersons();
    }

    @GetMapping("tech-subjects")
    public Integer numOfTechSubjects(@RequestParam String mail) throws Exception {
        return userService.numOfTechSubjects(mail);
    }

    @GetMapping("programming")
    public Integer numOfInterestedInProgramming() throws Exception {
        return userService.numOfInterestedInProgramming();
    }

    @DeleteMapping("delete-job")
    public void deleteJob(@RequestParam String mail, @RequestParam String jobTitle) throws Exception {
        userService.deleteJob(mail, jobTitle);
    }

    @PostMapping("add-job")
    public void addJob(@RequestParam String mail, @RequestBody JobDTO dto) throws Exception {
        userService.addJob(mail, dto);
    }
}
