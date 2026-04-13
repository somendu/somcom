/**
 * 
 */
package com.somendu.somweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.somendu.somweb.model.Image;

/**
 * <Description>
 * 
 * @author Somendu Maiti 
 *
 * Since 10-Apr-2026
 */
@Service
public class PortfolioService {

    public List<Image> getImages() {
        return List.of(
                new Image("/images/portfolio1.jpg", "Cambodia Island Bar"),
                new Image("/images/portfolio2.jpg", "Samudra Manthan Bangkok Airport"),
                new Image("/images/portfolio3.jpg", "Flower Decoration")
        );
    }
}