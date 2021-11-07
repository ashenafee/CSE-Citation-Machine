package com.ashenafee.csemachine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Metadata {
    private String doi;

    public Metadata(String doi) {
        this.doi = "https://api.crossref.org/works/" + doi.replace("/", "%2F");
    }

    protected HashMap<String, String> getMetadata() throws IOException {
        HashMap<String, String> metadata = new HashMap<>();
        
        // Connect to the URL using java's native library
        URL url = new URL(this.doi);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject().getAsJsonObject("message");
        
        // Save metadata
        String publisher = rootobj.get("publisher").getAsString();
        String title = rootobj.get("title").getAsString();
        String publishYear = rootobj.getAsJsonObject("published").getAsJsonArray("date-parts").get(0).getAsJsonArray().get(0).getAsString();
        String volume = rootobj.get("volume").getAsString();
        String journal = rootobj.getAsJsonArray("short-container-title").get(0).getAsString();
        String pages;
        try {
            pages = rootobj.get("page").getAsString().substring(0, rootobj.get("page").getAsString().indexOf("."));
        } catch (Exception e) {
            pages = rootobj.get("page").getAsString();
        }
        String issue;
        try {
            issue = "(" + rootobj.get("issue").getAsString() + ")";
        } catch (Exception e) {
            issue = "";
        }
        
        
        // Get authors
        JsonArray authors = rootobj.getAsJsonArray("author");
        ArrayList<String> authorList = new ArrayList<>();
        for (JsonElement author : authors) {
            authorList.add(author.getAsJsonObject().get("family").getAsString() + " " + author.getAsJsonObject().get("given").getAsString().charAt(0));
        }

        // Add metadata to hashmap
        metadata.put("publisher", publisher);
        metadata.put("doi", this.doi);
        metadata.put("title", title);
        metadata.put("year", publishYear);
        metadata.put("authors", String.join(", ", authorList));
        metadata.put("volume", volume);
        metadata.put("journal", journal);
        metadata.put("pages", pages);
        metadata.put("issue", issue);

        return metadata;
    }

    public static void main(String[] args) throws IOException {
        Metadata am = new Metadata("10.1126/science.abd4251");
        am.getMetadata();
    }
}