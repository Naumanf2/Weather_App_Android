package com.weather.app.tri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.weather.app.tri.Adapters.CitiesAdapter;
import com.weather.app.tri.CallBack.LocationCallBack;
import com.weather.app.tri.ModelClass.Cities;
import com.weather.app.tri.databinding.ActivityMainBinding;
import com.weather.app.tri.weatherAPI.WeatherViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity implements LocationCallBack {

    ActivityMainBinding binding;
    List<Cities> cityList = new ArrayList<>();
    WeatherViewModel weatherViewModel;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initializing Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //initializing ViewModel
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        citiesList();


        //set Gif to imageView using Binding through Glide
        Glide.with(this).load(R.raw.gifback).into(binding.imageBackground);


        CitiesAdapter adapter = new CitiesAdapter(cityList, this);
        binding.rclCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rclCities.setAdapter(adapter);


    }

    void citiesList() {

        // Adding cities with their coordinates
        cityList.add(new Cities("Karachi", "24.8607,67.0011"));
        cityList.add(new Cities("Lahore", "31.5497,74.3436"));
        cityList.add(new Cities("Islamabad", "33.6844,73.0479"));
        cityList.add(new Cities("Rawalpindi", "33.6396,73.0726"));
        cityList.add(new Cities("Faisalabad", "31.5497,73.0689"));
        cityList.add(new Cities("Multan", "30.1798,71.4214"));
        cityList.add(new Cities("Peshawar", "34.0151,71.5249"));
        cityList.add(new Cities("Quetta", "30.1798,66.9750"));
        cityList.add(new Cities("Sialkot", "32.4920,74.5319"));
        cityList.add(new Cities("Gujranwala", "32.1610,74.1883"));
        cityList.add(new Cities("Hyderabad", "25.3792,68.3682"));
        cityList.add(new Cities("Sukkur", "27.7131,68.8483"));
        cityList.add(new Cities("Larkana", "27.5600,68.2267"));
        cityList.add(new Cities("Gujrat", "32.5740,74.0754"));

        // More cities
        cityList.add(new Cities("Bahawalpur", "29.3956,71.6722"));
        cityList.add(new Cities("Sargodha", "32.0836,72.6711"));
        cityList.add(new Cities("Mirpur Khas", "25.5262,69.0119"));
        cityList.add(new Cities("Rahim Yar Khan", "28.4134,70.3035"));
        cityList.add(new Cities("Jhang", "31.4167,72.9833"));
    }

    private void showProgressDialog() {
        progressDialog = new Dialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.bg_rounded);
        }

        progressDialog.show();
    }

    @Override
    public void getLongitudeLatitude(String coordinates) {
        // Observe the weather data
        showProgressDialog();
        weatherViewModel.getCurrentWeather(coordinates).observe(this, weatherResponse -> {
            binding.textCity.setText(weatherResponse.getLocation().getName());
            binding.textStatus.setText(weatherResponse.getCurrent().getCondition().getText());
            binding.textTemp.setText(weatherResponse.getCurrent().getTemp_c() + " °C");
            Glide.with(this).load("https:" + weatherResponse.getCurrent().getCondition().getIcon()).into(binding.imgIcon);
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        });
    }
}