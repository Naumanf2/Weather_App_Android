package com.weather.app.tri.weatherAPI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.weather.app.tri.ModelClass.WeatherResponse;

import okhttp3.ResponseBody;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;
    private LiveData<WeatherResponse> weatherData;

    public WeatherViewModel() {
        weatherRepository = new WeatherRepository();
        weatherData = new MutableLiveData<>();
    }

    public LiveData<WeatherResponse> getCurrentWeather(String coordinates) {
        weatherData = weatherRepository.getCurrentWeather(coordinates);
        return weatherData;
    }
}