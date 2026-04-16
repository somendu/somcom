package com.somendu.somweb.controller;

import org.springframework.web.bind.annotation.*;
import com.somendu.somweb.dto.HomeViewDto;
import com.somendu.somweb.service.HomeViewService;

@RestController
@RequestMapping("/api/homeview")
@CrossOrigin(origins = "*")
public class HomeViewController {

    private final HomeViewService homeViewService;

    public HomeViewController(HomeViewService homeViewService) {
        this.homeViewService = homeViewService;
    }

    @GetMapping
    public HomeViewDto getHomeView() {
        return homeViewService.getHomeView();
    }
}