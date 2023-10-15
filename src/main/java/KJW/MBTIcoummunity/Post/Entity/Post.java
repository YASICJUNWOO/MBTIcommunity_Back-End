package KJW.MBTIcoummunity.Post.Entity;

import KJW.MBTIcoummunity.Post.Dto.PostCreateDto;
import KJW.MBTIcoummunity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "mbti_type")
    private String mbtiType;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    @Column(name = "likes", nullable = false)
    @ColumnDefault("0")
    private int likes;

    public static Post toEntity(PostCreateDto dto, User user) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .mbtiType(user.getMbtiType())
                .user(user)
                .likes(0)
                .build();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", mbtiType='" + mbtiType + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void updateLike(Post findPost) {
        this.id = findPost.getId();
        this.user = findPost.getUser();
        this.mbtiType = findPost.getMbtiType();
        this.title = findPost.getTitle();
        this.content = findPost.getContent();
        this.likes = findPost.getLikes() + 1;
    }
}
