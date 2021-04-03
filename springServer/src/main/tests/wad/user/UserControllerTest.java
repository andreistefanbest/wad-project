package wad.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import wad.user.entity.User;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Test
    void signIn() throws Exception {
        User u = new User();
        u.setFullName("Andrei");
        u.setMail("abc@def");
        when(userService.signIn(anyString(), anyString(), anyString())).thenReturn(u);

        mockMvc.perform(post("/user/signIn")
                                .param("name", "a")
                                .param("mail", "b")
                                .param("password", "11234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void logIn() {
    }

    @Test
    void getUser() {
    }

    @Test
    void buyPhone() {
    }
}