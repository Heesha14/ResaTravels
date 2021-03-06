package com.example.resatravels;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class Heesha_Admin_List_of_Vehicles extends AppCompatActivity {

    private DatabaseReference VehiclesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__list_of__vehicles);


        VehiclesRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Vehicle");

        recyclerView = findViewById(R.id.recyclerView_item);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Heesha_Vehicle> options =
                new FirebaseRecyclerOptions.Builder<Heesha_Vehicle>()
                        .setQuery(VehiclesRef, Heesha_Vehicle.class)
                        .build();


        FirebaseRecyclerAdapter<Heesha_Vehicle, Heesha_Vehicle_View_Holder> adapter =
                new FirebaseRecyclerAdapter<Heesha_Vehicle, Heesha_Vehicle_View_Holder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Heesha_Vehicle_View_Holder holder, int position, @NonNull final Heesha_Vehicle model) {
                        holder.txtvehicle_type.setText(model.getVehicle_type());
                        holder.txtvehicle_make.setText(model.getVehicle_make());
                        holder.txtvehicle_plate.setText(model.getVehicle_plate());
                        holder.txtvehicle_location.setText("Located at : "+model.getVehicle_location());
                        holder.txtvehicle_passenger.setText(model.getVehicle_passenger() + " maximum passengers");
                        holder.txtvehicle_price.setText("Rs. "+model.getVehicle_price() + " per day");
                        holder.txtvehicle_desc.setText(model.getVehicle_desc());
                        holder.txtvehicle_owner.setText("Owner : "+model.getVehicle_owner());
                        holder.txtvehicle_mobile.setText("Contact : "+model.getVehicle_mobile());
                        Picasso.get().load(model.getImage()).into(holder.vehicleView1);

                        holder.vehicleView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Heesha_Admin_List_of_Vehicles.this, Heesha_Maintain_Vehicle.class);
                                intent.putExtra("vid", model.getVid());
                                startActivity(intent);
                            }});
                    }

                    @NonNull
                    @Override
                    public Heesha_Vehicle_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_heesha_admin_vehicle_layout, parent, false);
                        Heesha_Vehicle_View_Holder holder = new Heesha_Vehicle_View_Holder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    public void redirectToListPlaces(View view) {
        Intent intent2 = new Intent(this, Heesha_Admin_List_of_Places.class);
        startActivity(intent2);
    }
    public void redirectToAdmin(View view){
        Intent intent2 = new Intent(this, Heesha_Admin_View.class);
        startActivity(intent2);
    }

}