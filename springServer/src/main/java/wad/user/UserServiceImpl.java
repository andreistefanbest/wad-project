
package wad.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import wad.user.dto.JobDTO;
import wad.user.dto.PersonDTO;
import wad.user.entities.*;
import wad.utils.GenericService;
import wad.utils.StringHasher;
import wad.utils.PersonDAO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Component()
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringHasher stringHasher;

    @Autowired
    private GenericService genericService;

    @Autowired
    private PersonDAO personDAO;

    @Override
    public User signIn(String name, String mail, String password) throws Exception {
        var user = new User();
        user.setFullName(name);
        user.setMail(mail);
        user.setPassword(stringHasher.getHash(password));
        user.setUserType("basic");

        return userRepository.save(user);
    }

    @Override
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

    @Override
    public User getUser(Integer id) throws Exception {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public List<PersonDTO> fetchPersons() throws Exception {
        return personDAO.read();
    }

    @Override
    public Integer numOfTechSubjects(String mail) throws Exception {
        return personDAO.numOfTechSubjects(mail);
    }

    @Override
    public Integer numOfInterestedInProgramming() throws Exception {
        return personDAO.numOfInterestedInProgramming();
    }

    @Override
    public void deleteJob(String mail, String jobTitle) throws Exception {
        personDAO.deleteJob(mail, jobTitle);
    }

    @Override
    public void addJob(String mail, JobDTO dto) throws Exception {
        personDAO.addJob(mail, dto);
    }
}
