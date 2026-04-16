package com.somendu.somweb.dto;

public record HomeViewDto(
        String[] imageUrls,
        String name,
        String title,
        String[] descriptions
) {}