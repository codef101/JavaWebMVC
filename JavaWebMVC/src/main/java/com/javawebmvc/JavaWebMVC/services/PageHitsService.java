package com.javawebmvc.JavaWebMVC.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PageHitsService {

    private final AtomicLong pageHits = new AtomicLong(0);

    @Async
    public void incrementAndGetPageHits() {
        pageHits.incrementAndGet();
    }

    public long getPageHits() {
        return pageHits.get();
    }
}
