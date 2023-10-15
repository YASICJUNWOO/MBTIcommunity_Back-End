package KJW.MBTIcoummunity.Auth.Service;

import KJW.MBTIcoummunity.Security.JWT.JwtTokenProvider;
import KJW.MBTIcoummunity.Security.JWT.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    //org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
    // 에러로 외부 treansactional 삭제해줌
    public TokenInfo login(String email, String password) {

        try{
            //전달 받은 정보들을 통해 토큰 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            TokenInfo token = jwtTokenProvider.generateToken(authentication);
            return token;
        }
        catch (Exception e){
            return null;
        }


    }


}
