package KJW.MBTIcoummunity.Post.Controller;

import KJW.MBTIcoummunity.Post.Dto.PostCreateDto;
import KJW.MBTIcoummunity.Post.Entity.Post;
import KJW.MBTIcoummunity.Post.Setvice.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://mbti.ap-northeast-2.elasticbeanstalk.com/

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService service;

    /**
    * @methodName : getPost
    * @param :
    * @return :
    * @Description:
    * @note: newest와 popular구분해야함
    **/
    @GetMapping("/list/new")
    public List<Post> getPost() {
        return service.getEntity();
    }

    @GetMapping("/list/hot")
    public List<Post> getHotPost() {
        return service.getHotPost();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEntity(@AuthenticationPrincipal UserDetails user, @RequestBody PostCreateDto dto) {
        try {
            return new ResponseEntity<>(service.createPost(user, dto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * @methodName : likePost
    * @param : postId
    * @return : 졸아요가 +1 된 수
    * @Description:
    * @note: 이후 좋아요를 누른 사용자도 저장
    **/
    @PostMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable("postId") Long postId){
        System.out.println("PostController.likePost");
        return new ResponseEntity<>( service.like(postId),HttpStatus.OK);
    }

}
