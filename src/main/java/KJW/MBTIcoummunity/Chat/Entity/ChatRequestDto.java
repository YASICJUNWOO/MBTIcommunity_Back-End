package KJW.MBTIcoummunity.Chat.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChatRequestDto {

    private String sender;

    private String content;
}
