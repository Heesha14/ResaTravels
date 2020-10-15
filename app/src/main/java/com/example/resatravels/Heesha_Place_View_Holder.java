package com.example.resatravels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class Heesha_Place_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtPlaceName, txtPlaceProvince, txtPlaceMobile,txtPlaceDescription;
    public ImageView imageView;
    public HeeshaPlaceClickLisetner listner;

    public Heesha_Place_View_Holder(View placeView)
    {
        super(placeView);


        imageView = (ImageView) placeView.findViewById(R.id.place_image);
        txtPlaceName = (TextView) placeView.findViewById(R.id.h_admin_add_place_input_name);
        txtPlaceProvince = (TextView) placeView.findViewById(R.id.h_admin_add_place_input_province);
        txtPlaceMobile = (TextView) placeView.findViewById(R.id.heesha_admin_add_place_input_mobile);
        txtPlaceDescription = (TextView) placeView.findViewById(R.id.h_admin_add_place_input_description);
    }

    public void setItemClickListner(HeeshaPlaceClickLisetner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }

}
