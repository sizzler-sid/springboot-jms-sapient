package com.training;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/employee")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello(){
        logger.debug("ABC");
        return "hello my friend.";
    }
}
