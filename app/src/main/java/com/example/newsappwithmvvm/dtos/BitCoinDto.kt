package com.example.newsappwithmvvm.dtos

data class BitCoinDto(
    var articles: List<Article>,
    var status: String,
    var totalResults: Int
) {
    data class Article(
        var author: String,
        var content: String,
        var description: String,
        var publishedAt: String,
        var source: Source,
        var title: String,
        var url: String,
        var urlToImage: String
    ) {
        data class Source(
            var id: String,
            var name: String
        )
    }
}