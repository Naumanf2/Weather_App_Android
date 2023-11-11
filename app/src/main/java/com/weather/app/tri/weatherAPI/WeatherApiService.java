package com.weather.app.tri.weatherAPI;

import com.weather.app.tri.ModelClass.WeatherResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiService {
    @Headers({
            "X-RapidAPI-Key: 63f7a00748msh02efaae3ea42c72p137881jsnaa995ec7eef8",
            "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    })
    @GET("current.json")
    Call<WeatherResponse> getCurrentWeather(@Query("q") String coordinates);
}
