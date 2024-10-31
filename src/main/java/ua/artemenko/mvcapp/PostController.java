package ua.artemenko.mvcapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @PostMapping
    public Post addPost(@RequestBody Post post){
        return postService.savePost(post);
    }
}
