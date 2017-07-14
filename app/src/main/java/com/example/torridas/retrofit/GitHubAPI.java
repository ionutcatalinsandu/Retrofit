package com.example.torridas.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Torridas on 13-Jul-17.
 */

public interface GitHubAPI {
    @GET("/search/repositories")
    Call<ManyResults> getMeRepos ( @Query("q") String query );
}
