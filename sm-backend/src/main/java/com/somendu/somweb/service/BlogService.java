/**
 * 
 */
package com.somendu.somweb.service;

import java.util.List;

import com.somendu.somweb.constants.TextConstants;
import com.somendu.somweb.dto.BlogPostDto;
import org.springframework.stereotype.Service;

/**
 * <Description>
 * 
 * @author Somendu Maiti 
 *
 * Since 10-Apr-2026
 */
@Service
public class BlogService {

    public List<BlogPostDto> getPosts() {
        return List.of(
                new BlogPostDto(TextConstants.BLOG_TITLE_1, TextConstants.BLOG_CONTENT_1),
                new BlogPostDto(TextConstants.BLOG_TITLE_2, TextConstants.BLOG_CONTENT_2)
        );
    }
}
