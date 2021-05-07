package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class Webscraper {
    private ArticleRepository articleRepository;
    public Webscraper(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void scrape(){
        try {
            String url = "https://news.ycombinator.com";
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("tr.athing");
            for(Element row : rows){
                String score = row.nextElementSibling().select("span.score").text();
                if (score.length() == 0){
                    continue;
                }
                score = score.replace(" points", "");
                int scoreNumber = Integer.parseInt(score);
                if(scoreNumber > 100){
                    System.out.println("\nScore: " + score );
                    String link = row.select("a.storylink").attr("href");
                    String title = row.select("a.storylink").text();

                    Article article = new Article();
                    article.setTitle(title);
                    article.setUrl(link);
                    article.setScore(scoreNumber);

                    articleRepository.save(article);
                }
            }
        }
        catch(Exception e){
            System.out.println("error in scraping: " + e.getMessage());
    }
    }
}
