package KJW.MBTIcoummunity.Chat;

import KJW.MBTIcoummunity.Chat.Entity.ChatRequestDto;
import KJW.MBTIcoummunity.Chat.Repository.ChatRepository;
import KJW.MBTIcoummunity.Chat.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void message(@RequestBody ChatRequestDto dto) {
        logger.warn(dto.getContent());
        messagingTemplate.convertAndSend("/sub/chat/1", dto);
    }

}
