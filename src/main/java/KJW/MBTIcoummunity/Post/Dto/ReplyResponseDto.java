package KJW.MBTIcoummunity.Post.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyResponseDto {

    private String content;
    private String username;

    public static ReplyResponseDto toDto(String content, String username) {
        return ReplyResponseDto.builder()
                .content(content)
                .username(username)
                .build();
    }
}
