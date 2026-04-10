/**
 * 
 */
package com.somendu.somweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.somendu.somweb.model.BlogPost;

/**
 * <Description>
 * 
 * @author Somendu Maiti 
 *
 * Since 10-Apr-2026
 */
@Service
public class BlogService {

    public List<BlogPost> getPosts() {
        return List.of(
                new BlogPost("First Blog", "This is my first post"),
                new BlogPost("Second Blog", "Learning Spring Boot")
        );
    }
}
