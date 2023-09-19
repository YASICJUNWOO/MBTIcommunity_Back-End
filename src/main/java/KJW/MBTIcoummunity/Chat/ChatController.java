package KJW.MBTIcoummunity.Chat;

import KJW.MBTIcoummunity.Chat.Entity.Chat;
import KJW.MBTIcoummunity.Chat.Entity.ChatRequestDto;
import KJW.MBTIcoummunity.Chat.Repository.ChatRepository;
import KJW.MBTIcoummunity.Chat.Repository.ChatRoomRepository;
import KJW.MBTIcoummunity.User.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void message(@RequestBody ChatRequestDto dto) {
        logger.warn(dto.getContent());
        messagingTemplate.convertAndSend("/sub/chat/1", dto);
    }

}
