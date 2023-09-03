package KJW.MBTIcoummunity.Post;

import KJW.MBTIcoummunity.Post.Dto.PostCreateDto;
import KJW.MBTIcoummunity.Post.Setvice.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://mbti.ap-northeast-2.elasticbeanstalk.com/

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {


    private final PostService service;

    @GetMapping("")
    public List<Post> getEntity() {
        return service.getEntity();
    }

    //@PostMapping("")
    //public ResponseEntity<?> createEntity(@RequestBody PostCreateDto dto) {
        //service.createPost(dto);
        //return ResponseEntity<>();
    //}

}
