package KJW.MBTIcoummunity.Chat.Repository;

import KJW.MBTIcoummunity.Chat.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
}
