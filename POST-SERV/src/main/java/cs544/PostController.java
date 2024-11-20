package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestHeader Map<String, String> headers, @RequestBody(required = false) String body) {

        headers.forEach((key, value) -> System.out.println(key + ": " + value));
        if (body != null) {
            System.out.println("Request Body: " + body);
        }
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestHeader("x-user-email") String email, @RequestBody Post post) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return null;
        }
        post.setUser(user);
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@RequestHeader("x-user-email") String email, @PathVariable Long id, @RequestBody Post postDetails) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return null;
        }
        postDetails.setUser(user);
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@RequestHeader("x-user-email") String email, @PathVariable Long id) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        // validate if post belongs to user
        Post post = postService.getPostById(id);
        if (post.getUser() == null || !Objects.equals(post.getUser().getId(), user.getId())) {
            return ResponseEntity.status(403).build();
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
