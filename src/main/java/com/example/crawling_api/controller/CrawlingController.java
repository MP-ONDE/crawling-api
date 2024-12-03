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
    public List<String> crawlParagraphs() {
        List<String> results = new ArrayList<>();
        try {
            String url = "https://ljs7143.tistory.com/270"; // 크롤링할 URL
            Document document = Jsoup.connect(url).get();

            // #content > div > div.entry-content > div.tt_article_useless_p_margin.contents_style 내의 모든 <p> 태그 선택
            Elements elements = document.select("#content > div > div.entry-content > div.tt_article_useless_p_margin.contents_style p");

            // <p> 태그의 텍스트 추가
            elements.forEach(element -> results.add(element.text()));
        } catch (Exception e) {
            results.add("크롤링 실패: " + e.getMessage());
        }
        return results;
    }
}
