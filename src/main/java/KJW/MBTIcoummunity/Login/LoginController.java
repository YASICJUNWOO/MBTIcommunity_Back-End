package KJW.MBTIcoummunity.Login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public void postLogin(LoginRequestDto dto) {
        loginService.isValid(dto);
    }

    @GetMapping("/success")
    public String getLogin() {
        return "success";
    }
}
