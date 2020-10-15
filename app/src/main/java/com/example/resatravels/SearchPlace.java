package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class SearchPlace extends AppCompatActivity {

    private Button SearchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private String SearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);
        inputText = findViewById(R.id.search_place_name);
        SearchBtn = findViewById(R.id.search_btn);
        searchList = findViewById(R.id.search_list);
        searchList.setLayoutManager(new LinearLayoutManager(SearchPlace.this));

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchText = inputText.getText().toString();

                onStart();
            }
        });

    }


    @Override
    public void onStart() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Heesha_Places_Model");
        super.onStart();
        FirebaseRecyclerOptions<Heesha_Places_Model> options = new FirebaseRecyclerOptions.Builder<Heesha_Places_Model>()
                .setQuery(reference.orderByChild("pname").startAt(SearchText),Heesha_Places_Model.class)
                .build();

        FirebaseRecyclerAdapter<Heesha_Places_Model,Heesha_Place_View_Holder> adapter = new FirebaseRecyclerAdapter<Heesha_Places_Model, Heesha_Place_View_Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Heesha_Place_View_Holder holder, int position, @NonNull final Heesha_Places_Model model)
            {
                holder.txtPlaceName.setText(model.getPname());
                holder.txtPlaceProvince.setText(model.getProvince());
                holder.txtPlaceMobile.setText(model.getMobile());
                holder.txtPlaceDescription.setText(model.getDescription());
                Picasso.get().load(model.getImage()).into(holder.imageView);

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(SearchPlace.this, Heesha_Maintain_Places.class);
                        intent.putExtra("pid", model.getPid());
                        startActivity(intent);
                    }});

            }

            @NonNull
            @Override
            public Heesha_Place_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_heesha_admin_places_layout, parent, false);
                Heesha_Place_View_Holder holder = new Heesha_Place_View_Holder(view);
                return holder;
            }
        };
        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}