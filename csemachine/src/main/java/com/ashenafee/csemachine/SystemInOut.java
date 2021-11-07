package com.ashenafee.csemachine;

import java.io.IOException;
import java.net.URL;

public class SystemInOut {

    public static void main(String[] args) throws IOException {
        // Get article input
        System.out.print("Enter your article's DOI:\t");
        Article article = new Article(System.console().readLine());
        
        // Return citation in CSE format
        System.out.println(article.citeAsCSE());
    }
}