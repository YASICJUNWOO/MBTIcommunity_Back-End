package KJW.MBTIcoummunity.Auth;

import KJW.MBTIcoummunity.Auth.Dto.LoginDto;
import KJW.MBTIcoummunity.Auth.Service.LoginService;
import KJW.MBTIcoummunity.Security.JWT.TokenInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginService loginService;

    private String accessToken;



    @DisplayName("토큰발급")
    @Test
    void login() throws Exception {

        //given
        LoginDto loginDto = LoginDto.builder()
                .email("TestEmail@email.com")
                .password("testpassword")
                .build();

        String reqJSON = new Gson().toJson(loginDto);

        //when

        MvcResult mvcResult = mockMvc.perform(
                post("/auth/login")
                        .contentType("application/json")
                        .content(reqJSON)
        ).andReturn();

        //Gson으로 이쁘게 json 출력
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        accessToken = gson.fromJson(mvcResult.getResponse().getContentAsString(), TokenInfo.class).getAccessToken();

        //then

        System.out.println("resJSON = " + accessToken);
    }

}