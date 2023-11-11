package com.weather.app.tri.weatherAPI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.weather.app.tri.ModelClass.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private WeatherApiService weatherApiService;
    private static final String BASE_URL = "https://weatherapi-com.p.rapidapi.com/";

    public WeatherRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApiService = retrofit.create(WeatherApiService.class);
    }

    public LiveData<WeatherResponse> getCurrentWeather(String coordinates) {
        MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();

        weatherApiService.getCurrentWeather(coordinates).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    weatherData.setValue(response.body());
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle failure
            }
        });

        return weatherData;
    }
}
