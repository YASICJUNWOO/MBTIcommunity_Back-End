package KJW.MBTIcoummunity.Post.Repository;

import KJW.MBTIcoummunity.Post.Entity.Post;
import KJW.MBTIcoummunity.Post.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByPost(Post post);
}
