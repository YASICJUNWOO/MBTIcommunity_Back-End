package KJW.MBTIcoummunity.Security;

import KJW.MBTIcoummunity.User.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/success")
    public ResponseEntity<?> success(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/fail")
    public ResponseEntity<?> fail() {
        logger.warn("로그인 실패");
        return new ResponseEntity<>("로그인 실패",HttpStatus.EXPECTATION_FAILED);
    }
}
