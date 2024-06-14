package org.unlogged.springwebfluxdemo.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * A test for HttpClient
 * */
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    private PostsClient postsClient;

    @GetMapping
    public Mono<String> getAllPosts() {
        return postsClient.findAll();
    }
}
