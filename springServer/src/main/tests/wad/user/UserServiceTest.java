package wad.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wad.user.entity.User;
import wad.user.repositories.user.UserRepository;
import wad.utils.StringHasher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    final String NAME = "Andrei";
    final String MAIL = "a@b";
    final String PASSWORD = "p";
    final String ID = "1";

    @Mock
    UserRepository userRepository;

    @Mock
    StringHasher stringHasher;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void signIn() throws Exception {
        when(userRepository.insert(any(User.class))).thenReturn(new User());

        User user = userService.signIn(NAME, MAIL, PASSWORD);

        assertNotNull(user);
        verify(userRepository).insert(any(User.class));
    }

    @Test
    void logIn() throws Exception {
        when(stringHasher.getHash(anyString())).thenAnswer(invocation -> invocation.getArguments()[0]);

        User u1 = new User();
        u1.setMail(MAIL);
        u1.setPassword(PASSWORD);

        User u2 = new User();
        u2.setMail(MAIL);
        u2.setPassword(PASSWORD + "1");

        User u3 = new User();
        u3.setMail(MAIL + "1");
        u3.setPassword(PASSWORD);

        when(userRepository.findAll()).thenReturn(List.of(u1, u2, u3));

        assertEquals(u1, userService.logIn(MAIL, PASSWORD));
        verify(userRepository).findAll();
    }

    @Test
    void getUser() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));

        assertNotNull(userService.getUser(ID));
    }

    @Test
    void buyPhone() {
    }
}