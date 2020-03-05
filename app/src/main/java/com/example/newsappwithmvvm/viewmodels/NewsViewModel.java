package com.example.newsappwithmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsappwithmvvm.dtos.BitCoinDto;
import com.example.newsappwithmvvm.repositories.NewsRepository;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<BitCoinDto> mutableLiveDataBitCoinNews = new MutableLiveData<>();
    private NewsRepository newsRepository;

    public void init() {
        newsRepository = NewsRepository.getInstance();
        //mutableLiveData = newsRepository.getNews("google-news", "37996235d5ec446b80e686b4cb238514");
//        mutableLiveDataBitCoinNews = newsRepository.getNewsForBitCoin("1", "37996235d5ec446b80e686b4cb238514");
        newsRepository.getNewsForBitCoin("1", "37996235d5ec446b80e686b4cb238514", mutableLiveDataBitCoinNews);
    }


    public void nextPage(Integer pageNo) {
        newsRepository.getNewsForBitCoin(pageNo.toString(),
                "37996235d5ec446b80e686b4cb238514", mutableLiveDataBitCoinNews);
    }

    public LiveData<BitCoinDto> getBitCoinNewsRepository() {
        return mutableLiveDataBitCoinNews;
    }
}