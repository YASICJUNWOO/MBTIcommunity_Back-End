package KJW.MBTIcoummunity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ENTITY {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "mbti_type")
    String mbtiType;

    @Column(name = "content")
    String content;

}
