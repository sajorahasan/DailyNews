package com.sajorahasan.dailynews.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sajora on 15-01-2017.
 */

public class NewsStore {
    private static List<Article> articles = new ArrayList<>();

    public static List<Article> getArticles() {
        return articles;
    }

    public static void setArticles(List<Article> articles) {
        NewsStore.articles = articles;
    }
}
