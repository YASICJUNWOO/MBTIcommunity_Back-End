package KJW.MBTIcoummunity.Post.Dto;

import lombok.Getter;

@Getter
public class ReplyPostDto {

    private String content;
    private Long postId;

    @Override
    public String toString() {
        return "ReplyPostDto{" +
                "content='" + content + '\'' +
                ", postId=" + postId +
                '}';
    }
}
