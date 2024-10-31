package ua.artemenko.mvcapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AppController {
    private final PostService postService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("newPost", new Post());
        return "posts";
    }

    @PostMapping("/posts")
    public String addPost(@ModelAttribute("newPost") Post newPost) {
        postService.savePost(newPost);
        return "redirect:/posts";
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.getPost(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "editPost";
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute("post") Post post) {
        postService.updatePost(id, post);
        return "redirect:/posts";
    }
}
