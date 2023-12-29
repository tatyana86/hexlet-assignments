package exercise.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import exercise.model.Post;

// BEGIN
@Getter
@Setter
public class PostDTO {
    private long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}
// END
