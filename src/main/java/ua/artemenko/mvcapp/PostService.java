package ua.artemenko.mvcapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Long id) { return postRepository.findById(id); }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post postToUpdate = postOptional.get();
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setContent(post.getContent());
            return postRepository.save(postToUpdate);
        } else {
            throw new PostNotFoundException("Post not found with ID: " + id);
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void asd(){
        var a = new int[]{1, 2, 3};
        Arrays.stream(a).average();
        List<int[]> list = Arrays.asList(a);
    }


}
