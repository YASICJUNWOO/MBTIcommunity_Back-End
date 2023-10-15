package KJW.MBTIcoummunity.Auth;

import KJW.MBTIcoummunity.Auth.Dto.LoginDto;
import KJW.MBTIcoummunity.Auth.Service.LoginService;
import KJW.MBTIcoummunity.Security.JWT.TokenInfo;
import KJW.MBTIcoummunity.Auth.Dto.UserCreateDto;
import KJW.MBTIcoummunity.User.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        System.out.println("loginDto = " + loginDto.getEmail()+loginDto.getPassword());
        TokenInfo token = loginService.login(loginDto.getEmail(), loginDto.getPassword());
        return (token!=null ? new ResponseEntity<>(token,HttpStatus.OK) :  new ResponseEntity<>("없음",HttpStatus.BAD_REQUEST));

    }
    @PostMapping("/signup")
    public ResponseEntity<?> postUser(@RequestBody UserCreateDto user) {
        String username = userService.createUser(user);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }


}
