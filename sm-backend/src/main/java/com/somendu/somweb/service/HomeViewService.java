package com.somendu.somweb.service;

import org.springframework.stereotype.Service;
import com.somendu.somweb.constants.ImageConstants;
import com.somendu.somweb.constants.TextConstants;
import com.somendu.somweb.dto.HomeViewDto;

@Service
public class HomeViewService {

    public HomeViewDto getHomeView() {
        return new HomeViewDto(
                ImageConstants.HOME_IMAGE_URLS,
                TextConstants.HOME_NAME,
                TextConstants.HOME_TITLE,
                TextConstants.HOME_DESCRIPTIONS
        );
    }
}
