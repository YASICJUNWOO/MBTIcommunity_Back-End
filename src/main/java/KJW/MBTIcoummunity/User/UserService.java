package KJW.MBTIcoummunity.User;

import KJW.MBTIcoummunity.User.Dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetails findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }

    @Transactional
    public String createUser(UserCreateDto user) {
        try {
            userRepository.findByEmail(user.getEmail()).ifPresent(m -> {
                throw new IllegalArgumentException("이미 가입된 유저입니다.");
            });

            //비밀번호 암호화
            String encodepwd = passwordEncoder.encode(user.getPassword());
            User savedUser = User.toEntity(user, encodepwd);

            //가입된 유저의 이름 반환
            return userRepository.save(savedUser).getUsername();
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
