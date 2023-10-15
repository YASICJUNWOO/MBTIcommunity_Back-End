package KJW.MBTIcoummunity.Global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    NOT_EXIST_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
