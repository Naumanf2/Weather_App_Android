package com.weather.app.tri.Adapters;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.app.tri.CallBack.LocationCallBack;
import com.weather.app.tri.ModelClass.Cities;
import com.weather.app.tri.R;
import com.weather.app.tri.databinding.ItemCitiesBinding;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {
    private List<Cities> cities;
    LocationCallBack callBack;

    private int selectedPosition = 1;


    public CitiesAdapter(List<Cities> cities, LocationCallBack callBack) {
        this.cities = cities;
        this.callBack = callBack;
        callBack.getLongitudeLatitude(cities.get(1).getCoordinates());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCitiesBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_cities, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cities city = cities.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.getLongitudeLatitude(city.getCoordinates());
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
        holder.bind(city.getName());

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCitiesBinding binding;

        public ViewHolder(ItemCitiesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(String city) {
            binding.setCity(city);

            int backgroundColor = getAdapterPosition() == selectedPosition ? R.color.teal_200 : R.color.white;
            int color = ContextCompat.getColor(binding.getRoot().getContext(), backgroundColor);

            binding.layoutBG.setBackgroundTintList(ColorStateList.valueOf(color));

            binding.executePendingBindings();
        }
    }
}
   