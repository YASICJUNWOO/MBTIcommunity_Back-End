package KJW.MBTIcoummunity.Security;

import KJW.MBTIcoummunity.User.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/success")
    public ResponseEntity<?> success() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fail")
    public ResponseEntity<?> fail() {
        logger.warn("로그인 실패");
        return new ResponseEntity<>("로그인 실패",HttpStatus.EXPECTATION_FAILED);
    }

}
