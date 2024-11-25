package com.example.crawling_api.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CrawlingController {

    @GetMapping("/crawl")
    public List<String> crawlSmallTalk() {
        List<String> results = new ArrayList<>();
        try {
            String url = "https://www.ringleplus.com/ko/student/landing/blog/ringle-smalltalk";
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("h3 + ol li");

            elements.stream().limit(9).forEach(e -> results.add(e.text()));
        } catch (Exception e) {
            results.add("크롤링 실패: " + e.getMessage());
        }
        return results;
    }
}
