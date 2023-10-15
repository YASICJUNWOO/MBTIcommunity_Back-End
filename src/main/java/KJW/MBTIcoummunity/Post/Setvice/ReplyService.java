package KJW.MBTIcoummunity.Post.Setvice;

import KJW.MBTIcoummunity.Post.Dto.ReplyPostDto;
import KJW.MBTIcoummunity.Post.Dto.ReplyResponseDto;
import KJW.MBTIcoummunity.Post.Entity.Post;
import KJW.MBTIcoummunity.Post.Entity.Reply;
import KJW.MBTIcoummunity.Post.Repository.PostRepository;
import KJW.MBTIcoummunity.Post.Repository.ReplyRepository;
import KJW.MBTIcoummunity.User.User;
import KJW.MBTIcoummunity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Transactional
    public void createReply(UserDetails user, ReplyPostDto dto) {
        try{
            User finduser = userRepository.findByUsername(user.getUsername()).get();

            Post findpost = postRepository.findById(dto.getPostId()).get();


            Reply entity = Reply.toEntity(finduser, dto.getContent(), findpost);

            replyRepository.save(entity);
        }
        catch (Exception e){
            throw new IllegalArgumentException("댓글 작성에 실패하였습니다.");
        }
    }

    @Transactional
    public List<ReplyResponseDto> getList(Long postId){
        Post findPost = postRepository.findById(postId).get();
        return replyRepository.findAllByPost(findPost).stream().map(
                r -> ReplyResponseDto.toDto(r.getContent(),r.getUser().getUsername())).toList();
    }
}
