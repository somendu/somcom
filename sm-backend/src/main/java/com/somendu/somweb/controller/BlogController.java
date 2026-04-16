/**
 * 
 */
package com.somendu.somweb.controller;

import com.somendu.somweb.dto.BlogPostDto;
import org.springframework.web.bind.annotation.RestController;
import com.somendu.somweb.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <Description>
 * 
 * @author Somendu Maiti 
 *
 * Since 10-Apr-2026
 */
@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<BlogPostDto> getBlogs() {
        return blogService.getPosts();
    }
}
