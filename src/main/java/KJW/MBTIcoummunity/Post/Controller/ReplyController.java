package KJW.MBTIcoummunity.Post.Controller;

import KJW.MBTIcoummunity.Post.Dto.ReplyPostDto;
import KJW.MBTIcoummunity.Post.Dto.ReplyResponseDto;
import KJW.MBTIcoummunity.Post.Setvice.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService service;

    @PostMapping
    public ResponseEntity<?> createReply(@AuthenticationPrincipal UserDetails user, @RequestBody ReplyPostDto dto) {
        service.createReply(user, dto);
        return new ResponseEntity<>(ReplyResponseDto.toDto(dto.getContent(), user.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getReplyList(@RequestParam("postId") Long postId) {
        return new ResponseEntity<>(service.getList(postId), HttpStatus.CREATED);
    }
}
