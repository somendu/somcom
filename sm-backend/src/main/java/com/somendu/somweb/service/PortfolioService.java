package com.somendu.somweb.service;

import java.util.List;

import com.somendu.somweb.constants.ImageConstants;
import com.somendu.somweb.constants.TextConstants;
import com.somendu.somweb.dto.ImageDto;
import org.springframework.stereotype.Service;

/**
 * <Description>
 * 
 * @author Somendu Maiti 
 *
 * Since 10-Apr-2026
 */
@Service
public class PortfolioService {

    public List<ImageDto> getImages() {
        return List.of(
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_1, TextConstants.PORTFOLIO_TITLE_1),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_2, TextConstants.PORTFOLIO_TITLE_2),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_3, TextConstants.PORTFOLIO_TITLE_3),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_4, TextConstants.PORTFOLIO_TITLE_4),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_5, TextConstants.PORTFOLIO_TITLE_5),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_6, TextConstants.PORTFOLIO_TITLE_6),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_9, TextConstants.PORTFOLIO_TITLE_9),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_10, TextConstants.PORTFOLIO_TITLE_10),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_12, TextConstants.PORTFOLIO_TITLE_12),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_13, TextConstants.PORTFOLIO_TITLE_13),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_14, TextConstants.PORTFOLIO_TITLE_14),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_15, TextConstants.PORTFOLIO_TITLE_15),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_16, TextConstants.PORTFOLIO_TITLE_16),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_17, TextConstants.PORTFOLIO_TITLE_17),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_18, TextConstants.PORTFOLIO_TITLE_18),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_19, TextConstants.PORTFOLIO_TITLE_19),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_20, TextConstants.PORTFOLIO_TITLE_20),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_21, TextConstants.PORTFOLIO_TITLE_21),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_22, TextConstants.PORTFOLIO_TITLE_22),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_23, TextConstants.PORTFOLIO_TITLE_23),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_24, TextConstants.PORTFOLIO_TITLE_24),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_25, TextConstants.PORTFOLIO_TITLE_25),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_26, TextConstants.PORTFOLIO_TITLE_26),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_27, TextConstants.PORTFOLIO_TITLE_27),
                new ImageDto(ImageConstants.PORTFOLIO_IMAGE_28, TextConstants.PORTFOLIO_TITLE_28)
        );
    }
}
