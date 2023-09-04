package KJW.MBTIcoummunity.User;

import KJW.MBTIcoummunity.User.Dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder  passwordEncoder;

    public UserDetails findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }

    public User createUser(UserCreateDto user) {
        String encodepwd = passwordEncoder.encode(user.getPassword());
        User savedUser = User.toEntity(user, encodepwd);

        return userRepository.save(savedUser);
    }
}
