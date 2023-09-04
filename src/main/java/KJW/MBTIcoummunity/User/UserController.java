package KJW.MBTIcoummunity.User;

import KJW.MBTIcoummunity.User.Dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class.getName());

    private final UserService userService;

    @PostMapping("/signup")
    public User postUser(@RequestBody UserCreateDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
