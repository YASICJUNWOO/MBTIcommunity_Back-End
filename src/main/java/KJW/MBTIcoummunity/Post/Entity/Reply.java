package KJW.MBTIcoummunity.Post.Entity;

import KJW.MBTIcoummunity.Post.Dto.ReplyPostDto;
import KJW.MBTIcoummunity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "post")
    @ManyToOne
    private Post post;

    @Column(name = "content")
    private String content;

    public static Reply toEntity(User user,String content, Post post) {
        return Reply.builder()
                .content(content)
                .user(user)
                .post(post)
                .build();
    }

}
