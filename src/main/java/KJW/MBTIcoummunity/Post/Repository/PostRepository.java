package KJW.MBTIcoummunity.Post.Repository;

import KJW.MBTIcoummunity.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByLikesDesc();
}
