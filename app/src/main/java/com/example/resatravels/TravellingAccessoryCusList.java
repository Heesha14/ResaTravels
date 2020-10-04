package com.example.resatravels;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class TravellingAccessoryCusList extends ArrayAdapter<TravellingAccessory> {

    private Activity context;
    private List<TravellingAccessory> travellingAccessoryList;
    DatabaseReference databaseReference;

    public TravellingAccessoryCusList(Activity context, List<TravellingAccessory> travellingAccessoryList) {
        super(context,R.layout.activity_travelling_accessory_cus, travellingAccessoryList);
        this.context = context;
        this.travellingAccessoryList = travellingAccessoryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.row_data, null , true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.accessoryName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.price);
        TextView textViewCurrencyType = (TextView) listViewItem.findViewById(R.id.currencyType);

        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.imageView12);

        final TravellingAccessory travellingAccessory = travellingAccessoryList.get(position);
        textViewName.setText(travellingAccessory.getName());
        textViewPrice.setText(travellingAccessory.getPrice());


        return listViewItem;
    }
}
