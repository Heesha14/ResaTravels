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

public class Heesha_Admin_List_of_Places extends AppCompatActivity {

    private DatabaseReference PlacesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__list_of__places);

        PlacesRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Places_Model");

        recyclerView = findViewById(R.id.h_recyclerView_places);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Heesha_Places_Model> options =
                new FirebaseRecyclerOptions.Builder<Heesha_Places_Model>()
                        .setQuery(PlacesRef, Heesha_Places_Model.class)
                        .build();

        FirebaseRecyclerAdapter<Heesha_Places_Model, Heesha_Place_View_Holder> adapter =
                new FirebaseRecyclerAdapter<Heesha_Places_Model, Heesha_Place_View_Holder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Heesha_Place_View_Holder holder, int position, @NonNull final Heesha_Places_Model model)
                    {
                        holder.txtPlaceName.setText(model.getPname());
                        holder.txtPlaceProvince.setText(model.getProvince() + " Province");
                        holder.txtPlaceMobile.setText(model.getMobile());
                        holder.txtPlaceDescription.setText(model.getDescription());
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                        holder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Heesha_Admin_List_of_Places.this, Heesha_Maintain_Places.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                            }});



                    }

                    @NonNull
                    @Override
                    public Heesha_Place_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_heesha_admin_places_layout, parent, false);
                        Heesha_Place_View_Holder holder = new Heesha_Place_View_Holder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public void redirectToAdmin(View view){
        Intent intent2 = new Intent(this, Heesha_Admin_View.class);
        startActivity(intent2);
    }

}