package pt.mleiria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageViewController {
    private static final Logger log = LoggerFactory.getLogger(PageViewController.class);

    @GetMapping("/index")
    public String index() {
        log.info("Index started");
        return "index"; // Thymeleaf template name
    }

    @GetMapping("/test")
    public String test() {
        log.info("D3 started");
        return "tested3"; // Thymeleaf template name
    }

    @GetMapping("/home")
    public String home() {
        log.info("Home started");
        return "home"; // Thymeleaf template name
    }
}
