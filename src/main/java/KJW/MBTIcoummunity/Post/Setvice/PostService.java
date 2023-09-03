package KJW.MBTIcoummunity.Post.Setvice;

import KJW.MBTIcoummunity.Post.Dto.PostCreateDto;
import KJW.MBTIcoummunity.Post.Post;
import KJW.MBTIcoummunity.Post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public List<Post> getEntity() {
        return repository.findAll();
    }

    //public Post createPost(PostCreateDto dto) {
        //Post post = Post.toEntity(dto);
        //repository.save(post);
        //return post;
    //}
}
