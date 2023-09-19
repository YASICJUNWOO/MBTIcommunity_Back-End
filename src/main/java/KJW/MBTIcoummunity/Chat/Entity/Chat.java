package KJW.MBTIcoummunity.Chat.Entity;

import KJW.MBTIcoummunity.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @Column(name = "content")
    private String content;

    @Column(name = "time")
    private LocalDateTime time;
}
