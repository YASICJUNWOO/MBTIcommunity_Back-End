package KJW.MBTIcoummunity.User;

import KJW.MBTIcoummunity.Auth.Dto.UserCreateDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc //모의 mvc
@Transactional
class UserControllerTest {

    //모의 의존 관계 주입

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("회원가입")
    @Test
    @WithMockUser
    void postUser() throws Exception {

        //given
        UserCreateDto userCreateDto = UserCreateDto.builder().username("testUser").email("testEmail@email.com").mbtiType("ISTJ").password("testpassword").build();

        String reqJSON = new Gson().toJson(userCreateDto);

        //when
        MvcResult mvcResult = mockMvc.perform(post("/api/signup").contentType("application/json").content(reqJSON)).andReturn();

        String resJSON = mvcResult.getResponse().getContentAsString();

        //then

        Assertions.assertEquals(userCreateDto.getUsername(), resJSON);
    }
}
