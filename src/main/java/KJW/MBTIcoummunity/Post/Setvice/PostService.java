package KJW.MBTIcoummunity.Post.Setvice;

import KJW.MBTIcoummunity.Post.Dto.PostCreateDto;
import KJW.MBTIcoummunity.Post.Entity.Post;
import KJW.MBTIcoummunity.Post.Repository.PostRepository;
import KJW.MBTIcoummunity.User.User;
import KJW.MBTIcoummunity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public List<Post> getEntity() {
        return repository.findAll();
    }

    @Transactional
    public List<Post> getHotPost() {
        return repository.findAllByOrderByLikesDesc();
    }

    public Post createPost(UserDetails user, PostCreateDto dto) {

        //유저 정보 가져오기
        User findUser = userRepository.findByUsername(user.getUsername()).get();

        Post post = Post.toEntity(dto,findUser);

        return repository.save(post);
    }

    @Transactional
    public int like(Long postId) {
        //주어진 post 찾고 없으면 예외 반환
        Post findPost = repository.findById(postId).orElseThrow(()->new IllegalArgumentException());
        findPost.updateLike(findPost);
        return findPost.getLikes()+1;

    }


}
