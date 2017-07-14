package com.example.torridas.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Torridas on 13-Jul-17.
 */

public class ManyResults {

    @SerializedName("total_count")
    private Long totalCount;
    @SerializedName("items")
    private List<Result> results;

    public ManyResults(Long totalCount, List<Result> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public ManyResults() {

    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
