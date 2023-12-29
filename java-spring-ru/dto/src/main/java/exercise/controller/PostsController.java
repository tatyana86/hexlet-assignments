package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var res = posts.stream().map(this::toDTO).toList();
        return res;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return toDTO(post);

    }
    private PostDTO toDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        var comments = commentRepository.findByPostId(post.getId());
        var commentsDto = comments.stream()
                .map((comment) -> {
                    var commentDto = new CommentDTO();
                    commentDto.setBody(comment.getBody());
                    commentDto.setId(comment.getId());
                    return commentDto;
                })
                .toList();
        dto.setComments(commentsDto);
        return dto;
    }
}

// END
