package com.training.utility;

import com.training.dto.SwipeRequestDTO;
import com.training.dto.SwipeResponseDTO;
import com.training.entity.Swipe;

public class SwipeObjectMapper {

    public static SwipeResponseDTO swipeToSwipeDTO(Swipe swipe) {

        if(swipe==null)
            return null;
        SwipeResponseDTO swipeRespDTO = new SwipeResponseDTO();
        swipeRespDTO.setId(swipe.getId());
        swipeRespDTO.setSwipeType(swipe.getSwipeType());
        swipeRespDTO.setDateTime(swipe.getDateTime());
        swipeRespDTO.setDate(swipe.getDate());
        return swipeRespDTO;

    }

    public static Swipe swipeDTOToSwipe(SwipeRequestDTO swipeDTO) {

        if(swipeDTO==null)
            return null;
        Swipe swipe = new Swipe();
        swipe.setSwipeType(swipeDTO.getSwipeType());
        swipe.setDateTime(swipeDTO.getDateTime());
        swipe.setDate(swipeDTO.getDate());
        return swipe;

    }

    public static Swipe swipeDTOToSwipe(SwipeResponseDTO swipeDTO) {

        if(swipeDTO==null)
            return null;
        Swipe swipe = new Swipe();
        swipe.setSwipeType(swipeDTO.getSwipeType());
        swipe.setDateTime(swipeDTO.getDateTime());
        swipe.setDate(swipeDTO.getDate());
        return swipe;

    }

}
