package exercise.controller;

import exercise.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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

    //get
    @GetMapping
    public List<Comment> index () {
        return commentRepository.findAll();
    }
    //GET {id}
    @GetMapping("/{id}")
    public Comment show(@PathVariable long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        return comment;
    }
    //POST
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
    //PUT {id}
    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment commentData) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        comment.setBody(commentData.getBody());
        comment.setPostId(commentData.getPostId());

        commentRepository.save(comment);
        return comment;
    }
    //DELETE {id}
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END