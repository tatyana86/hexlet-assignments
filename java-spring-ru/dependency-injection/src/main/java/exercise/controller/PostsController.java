package exercise.controller;

import exercise.handler.GlobalExceptionHandler;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    //GET
    @GetMapping("")
    public List<Post> index() {
        return postRepository.findAll();
    }
    //GET {id}
    @GetMapping("/{id}")
    public Post show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return post;
    }
    //POST
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create (@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }
    //PUT {id}
    @PutMapping("/{id}")
    public Post update(@PathVariable long id, @RequestBody Post postData) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        post.setTitle(postData.getTitle());
        post.setBody(postData.getBody());
        postRepository.save(post);
        return post;
    }
    //DELETE {id}
    @DeleteMapping("{id}")
    public void destroy(@PathVariable long id) {
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }

}