
package sw.user;

import org.springframework.stereotype.Service;
import sw.user.dto.JobDTO;
import sw.user.dto.PersonDTO;
import sw.user.entities.User;

import java.util.List;

/**
 *
 * @author Andrei Stefan
 * @since Mar 24, 2019
 */
@Service
public interface UserService {

    User signIn(String name, String mail, String password) throws Exception;
    User logIn(String mail, String password) throws Exception;
    User getUser(Integer id) throws Exception;

    List<PersonDTO> fetchPersons() throws Exception;

    Integer numOfTechSubjects(String mail) throws Exception;

    Integer numOfInterestedInProgramming() throws Exception;

    void deleteJob(String mail, String jobTitle) throws Exception;

    void addJob(String mail, JobDTO dto) throws Exception;
}
