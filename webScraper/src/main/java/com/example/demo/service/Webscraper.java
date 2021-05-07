package com.example.demo.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class Webscraper {

    public static void main(String[] args) {
        new Webscraper();
    }

    public Webscraper() {
        scrape();
    }

    void scrape(){
        try {

        String url = "https://news.ycombinator.com";
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(oracle.openStream()));
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
                    System.out.println("link: " + row.select("a.storylink").attr("href"));
                    System.out.println("Title: " + row.select("a.storylink").text() + "\n");
                }
            }

//        String inputLine;
//        FileWriter writer = new FileWriter("p.html");
//        while ((inputLine = in.readLine()) != null)
//            writer.write(inputLine);
////            System.out.println(inputLine);
//        in.close();
        }
        catch(Exception e){
            System.out.println("error in scraping: " + e.getMessage());
    }
    }
}
