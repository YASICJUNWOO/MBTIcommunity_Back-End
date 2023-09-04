package KJW.MBTIcoummunity.Login;

import KJW.MBTIcoummunity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public void isValid(LoginRequestDto dto) {
        userRepository.findByEmail(dto.getEmail()).orElseThrow(()->new IllegalArgumentException(dto.getEmail()));
    }
}
