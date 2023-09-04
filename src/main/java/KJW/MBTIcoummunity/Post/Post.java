package KJW.MBTIcoummunity.Post;

import KJW.MBTIcoummunity.Post.Dto.PostCreateDto;
import KJW.MBTIcoummunity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    User user;

    @Column(name = "mbti_type")
    String mbtiType;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    public static Post toEntity(PostCreateDto dto, User user){
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .mbtiType(user.getMbtiType())
                .user(user)
                .build();
    }

}
