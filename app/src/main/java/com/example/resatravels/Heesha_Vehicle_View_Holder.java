package com.example.resatravels;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class Heesha_Vehicle_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtvehicle_type, txtvehicle_make, txtvehicle_plate,txtvehicle_location, txtvehicle_passenger, txtvehicle_price,
            txtvehicle_desc, txtvehicle_owner,txtvehicle_mobile;
    public ImageView vehicleView1;
    public HeeshaVehicleClickLisetner listner;


    public Heesha_Vehicle_View_Holder(View vehicleView)
    {
        super(vehicleView);

        vehicleView1 = (ImageView) vehicleView.findViewById(R.id.h_vehicle_image);
        txtvehicle_type = (TextView) vehicleView.findViewById(R.id.h_input_type);
        txtvehicle_make = (TextView) vehicleView.findViewById(R.id.h_input_make);
        txtvehicle_plate = (TextView) vehicleView.findViewById(R.id.h_input_plate);
        txtvehicle_location = (TextView) vehicleView.findViewById(R.id.h_input_location);
        txtvehicle_passenger = (TextView) vehicleView.findViewById(R.id.h_input_people);
        txtvehicle_price = (TextView) vehicleView.findViewById(R.id.h_input_price);
        txtvehicle_desc = (TextView) vehicleView.findViewById(R.id.h_input_desc);
        txtvehicle_owner = (TextView) vehicleView.findViewById(R.id.h_input_name);
        txtvehicle_mobile = (TextView) vehicleView.findViewById(R.id.h_input_mobile);
    }

    public void setItemClickListner(HeeshaVehicleClickLisetner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}