package com.example.catapp.Interface;

import com.example.catapp.ui.Model.Data;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataApi {
    String API_ROUTE="/fact";

    @GET(API_ROUTE)
    Call<Data> getPosts();

}
