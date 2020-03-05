package com.example.newsappwithmvvm.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.newsappwithmvvm.dtos.BitCoinDto;
import com.example.newsappwithmvvm.network.APIEndPoints;
import com.example.newsappwithmvvm.network.RetrofitService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;
    private APIEndPoints apiEndPoints;

    private NewsRepository() {
        apiEndPoints = RetrofitService.createService(APIEndPoints.class);
    }

    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

//    public MutableLiveData<NewsResponse> getNews(String source, String key) {
//        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
//        apiEndPoints.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(@NotNull Call<NewsResponse> call,
//                                   @NotNull Response<NewsResponse> response) {
//                if (response.isSuccessful()) {
//                    newsData.setValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<NewsResponse> call, @NotNull Throwable t) {
//                newsData.setValue(null);
//            }
//        });
//        return newsData;
//    }

    public MutableLiveData<BitCoinDto> getNewsForBitCoin(String page, String key) {
        final MutableLiveData<BitCoinDto> newsData = new MutableLiveData<>();
        apiEndPoints.getNewsForBitCoin(page, key).enqueue(new Callback<BitCoinDto>() {
            @Override
            public void onResponse(@NotNull Call<BitCoinDto> call,
                                   @NotNull Response<BitCoinDto> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<BitCoinDto> call, @NotNull Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

    public void getNewsForBitCoin(String page, String key, MutableLiveData<BitCoinDto> mutableLiveData) {
        apiEndPoints.getNewsForBitCoin(page, key).enqueue(new Callback<BitCoinDto>() {
            @Override
            public void onResponse(@NotNull Call<BitCoinDto> call,
                                   @NotNull Response<BitCoinDto> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<BitCoinDto> call, @NotNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
    }
}