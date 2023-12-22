package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import exercise.model.Post;
import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        return comment;
    }

    @PostMapping("")
    public Comment create(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment updatedComment) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        comment.setTitle(updatedComment.getTitle());
        comment.setBody(updatedComment.getBody());
        commentRepository.save(comment);
        retun comment;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END
