package com.example.newsappwithmvvm.network;

import com.example.newsappwithmvvm.dtos.BitCoinDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndPoints {
//    @GET("top-headlines")
//    Call<NewsResponse> getNewsList(@Query("sources") String newsSource,
//                                   @Query("apiKey") String apiKey);

    @GET("everything?q=bitcoin")
    Call<BitCoinDto> getNewsForBitCoin(@Query("page") String page,
                                       @Query("apiKey") String apiKey);
}