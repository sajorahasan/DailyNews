package com.sajorahasan.dailynews.networking;

import com.sajorahasan.dailynews.model.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sajora on 15-01-2017.
 */

public class NewsAPI {
    private static final String APIKEY = "9ff1c6d0c7de42e390866f432bfea73f";
    private static final String APIPATH = "https://newsapi.org/v1/";

    private static NewsService newsService = null;

    public static NewsService getApi() {
        if (newsService == null) {
            //initialize NewsService
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            newsService = retrofit.create(NewsService.class);
        }

        return newsService;
    }

    public interface NewsService {

        @GET("articles?apiKey=" + APIKEY)
        Call<GetArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}
