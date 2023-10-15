package KJW.MBTIcoummunity.Security.JWT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TokenInfo {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}
