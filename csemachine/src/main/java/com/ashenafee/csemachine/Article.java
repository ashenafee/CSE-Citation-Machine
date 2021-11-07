package com.ashenafee.csemachine;

import java.io.IOException;
import java.util.HashMap;

public class Article {

    private String doi;
    private HashMap<String, String> metadata;

    public Article(String doi) {
        this.doi = doi;
    }

    private void getMetadata() {
        Metadata md = new Metadata(this.doi);
        try {
            this.metadata = md.getMetadata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String citeAsCSE() {
        getMetadata();
        String title = metadata.get("title");
        String authors = metadata.get("authors");
        String journal = metadata.get("journal");
        String year = metadata.get("year");
        String volume = metadata.get("volume");
        String pages = metadata.get("pages");
        String issue = metadata.get("issue");
        
        return String.format("%s. %s. %s. %s %s%s: %s", authors, year, title, journal, volume, issue, pages);
    }

    public String getDoi() {
        return this.doi;
    }

    public String getTitle() {
        getMetadata();
        return this.metadata.get("title");
    }

    public static void main(String[] args) {
        Article article = new Article("10.1016/j.cell.2020.02.058");
        article.citeAsCSE();
    }
    
}