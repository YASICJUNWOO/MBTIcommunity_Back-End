package KJW.MBTIcoummunity.User;

import KJW.MBTIcoummunity.User.Dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder  passwordEncoder;

    private final UserRepository userRepository;

    public User createUser(UserCreateDto user) {
        String encodepwd = passwordEncoder.encode(user.getPassword());
        User savedUser = User.toEntity(user, encodepwd);

        return userRepository.save(savedUser);
    }
}
