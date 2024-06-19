package com.example.movielistapp.pojo;

import java.util.List;

public class MovieModel {
    private long page;
    private long total_pages;
    private List<MovieResultModel> results;

    public MovieModel(long page, long total_pages, List<MovieResultModel> results) {
        this.page = page;
        this.total_pages = total_pages;
        this.results = results;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(long total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieResultModel> getResults() {
        return results;
    }

    public void setResults(List<MovieResultModel> results) {
        this.results = results;
    }
}

