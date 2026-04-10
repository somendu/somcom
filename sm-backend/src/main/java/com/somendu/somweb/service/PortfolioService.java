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
                new Image("/images/porfolio-1-300x500.jpg", "Leh Trip"),
                new Image("/images/porfolio-2-300x500.jpg", "Bangkok Trip")
        );
    }
}