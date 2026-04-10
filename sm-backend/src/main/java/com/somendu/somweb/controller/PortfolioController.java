/**
 * 
 */
package com.somendu.somweb.controller;

import org.springframework.web.bind.annotation.*;

import com.somendu.somweb.model.Image;
import com.somendu.somweb.service.PortfolioService;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<Image> getPortfolio() {
        return portfolioService.getImages();
    }
}