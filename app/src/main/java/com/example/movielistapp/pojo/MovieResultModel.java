package com.example.movielistapp.pojo;

import java.io.Serializable;

public class MovieResultModel implements Serializable{
    private long id;
    private String overview;
    private String poster_path;
    private String title;
    private double vote_average;
    private long vote_count;

    public MovieResultModel(long id, String overview, String poster_path, String title, double vote_average, long vote_count) {
        this.id = id;
        this.overview = overview;
        this.poster_path = poster_path;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public long getVote_count() {
        return vote_count;
    }

    public void setVote_count(long vote_count) {
        this.vote_count = vote_count;
    }
}
