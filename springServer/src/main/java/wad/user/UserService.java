
package wad.user;

import org.springframework.stereotype.Service;
import wad.user.dto.JobDTO;
import wad.user.dto.PersonDTO;
import wad.user.entities.User;

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
