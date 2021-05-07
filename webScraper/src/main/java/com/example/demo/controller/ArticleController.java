package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.Webscraper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final Webscraper webscraper;
    private final ArticleRepository articleRepository;

    public ArticleController(Webscraper webscraper, ArticleRepository articleRepository){
        this.webscraper = webscraper;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/scrape")
    public ResponseEntity<String> webscrape(){
        webscraper.scrape();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles(){
        List<Article> articles = new ArrayList<>();
        for(Article a : articleRepository.findAll()){
            articles.add(a);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
