package KJW.MBTIcoummunity.Controller;

import KJW.MBTIcoummunity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    Repository repository;

    @GetMapping("")
    public List<ENTITY> hello() {
        return repository.findAll();
    }

    @PostMapping("")
    public ENTITY post(@RequestBody ENTITY entity) {
        ENTITY saved = repository.save(entity);
        return saved;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest dto) {
        System.out.println("HelloController.login");
        System.out.println(dto.getUsername());

        LoginResponse response;

        if (userRepository.findByUsername(dto.getUsername()) != null) {
            User findedUser = userRepository.findByUsername(dto.getUsername());
            response = new LoginResponse().
                    builder()
                    .success("true")
                    .mbtiType(findedUser.getMbtiType())
                    .build();
        }
        else {
            response = new LoginResponse().
                    builder()
                    .success("false")
                    .mbtiType("")
                    .build();
        }

        return response;
    }

    @PostMapping("/signup")
    public signupResponse signup(@RequestBody signupRequest dto) {
        logger.info("Received signup request: {}", dto);

        signupResponse response;

        if (userRepository.findByUsername(dto.getUsername()) == null) {
            User user = User.toEntity(dto);
            System.out.println("user = " + user.getUsername());

            User saveUser = userRepository.save(user);
            response = new signupResponse().builder().success("true").build();
        } else {
            response = new signupResponse().builder().success("false").build();
        }

        return response;
    }
}
