package KJW.MBTIcoummunity.Auth.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    String username;

    String email;

    String password;

    String mbtiType;
}
