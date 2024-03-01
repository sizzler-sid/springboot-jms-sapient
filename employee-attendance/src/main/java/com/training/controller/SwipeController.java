package com.training.controller;

import com.training.dto.SwipeRequestDTO;
import com.training.dto.SwipeResponseDTO;
import com.training.entity.Swipe;
import com.training.service.Impl.SwipeServiceImpl;
import com.training.utility.SwipeObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/swipe")
public class SwipeController {

    @Autowired
    SwipeServiceImpl swipeServiceImpl;

    @PostMapping("/in/{empId}")
    public ResponseEntity<SwipeResponseDTO> createSwipeInRecord(@PathVariable int empId) {
        Swipe swipeInRecord = swipeServiceImpl.createSwipeInRecord(empId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SwipeObjectMapper.swipeToSwipeDTO(swipeInRecord));
    }

    @PostMapping("/out/{empId}")
    public ResponseEntity<SwipeResponseDTO> createSwipeOutRecord(@PathVariable int empId) {
        Swipe swipeOutRecord = swipeServiceImpl.createSwipeOutRecord(empId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SwipeObjectMapper.swipeToSwipeDTO(swipeOutRecord));
    }

    @PostMapping("/{empId}")
    public ResponseEntity<SwipeResponseDTO> createCustomSwipeRecord(@RequestBody SwipeRequestDTO swipeDTO, @PathVariable int empId) {
        Swipe customSwipeRecord = swipeServiceImpl.createCustomSwipeRecord(SwipeObjectMapper.swipeDTOToSwipe(swipeDTO), empId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SwipeObjectMapper.swipeToSwipeDTO(customSwipeRecord));
    }
}
