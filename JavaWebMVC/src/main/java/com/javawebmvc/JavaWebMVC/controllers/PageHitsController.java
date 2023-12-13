package com.javawebmvc.JavaWebMVC.controllers;

import com.javawebmvc.JavaWebMVC.services.PageHitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageHitsController {

    private final PageHitsService pageHitsService;

    @Autowired
    public PageHitsController(PageHitsService pageHitsService) {
        this.pageHitsService = pageHitsService;
    }

    @ResponseBody
    @GetMapping("/api/page-hits")
    public long getPageHits() {
        return pageHitsService.getPageHits();
    }

    @Async
    @Scheduled(fixedRateString = "${page.hits.interval.seconds:3000}")
    public void incrementPageHits() {
        pageHitsService.incrementAndGetPageHits();
        System.out.println("Page hits incremented");
    }
}
