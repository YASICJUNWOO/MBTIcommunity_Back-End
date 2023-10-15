package KJW.MBTIcoummunity.Post;

import KJW.MBTIcoummunity.Auth.Dto.LoginDto;
import KJW.MBTIcoummunity.Security.JWT.JwtTokenProvider;
import KJW.MBTIcoummunity.Security.JWT.TokenInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PostControllerTest {

    private String accessToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        //MockMvc 설정
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                //.addFilters(new JwtAuthenticationFilter(jwtTokenProvider))
                //.addFilters(new UsernamePasswordAuthenticationFilter())
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

    }

    @DisplayName("토큰발급")
    @BeforeEach
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
    }

    @DisplayName("토큰 값 여부에 따른 작동 테스트")
    @Test
    void getEntity() throws Exception {

        //given

        //when

        MvcResult mvcResult = mockMvc.perform(
                        get("/post/list")
                                .contentType("application/json")
                               .header("Authorization", "Bearer " + accessToken)
                )
                .andReturn();

        int resCode = mvcResult.getResponse().getStatus();

        //then

        //인증 통과됐다면 assert 통과
        Assertions.assertEquals(200, resCode);

    }
}