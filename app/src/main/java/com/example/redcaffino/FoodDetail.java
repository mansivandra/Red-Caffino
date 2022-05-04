package com.example.redcaffino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.redcaffino.model.food;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {

    TextView food_name,food_price,food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btncart;
    ElegantNumberButton numberButton;

    String foodId="";

    FirebaseDatabase database;
    DatabaseReference foods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //firebase
        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Food");

        //init view
        numberButton=(ElegantNumberButton) findViewById(R.id.number_button);
        btncart=(FloatingActionButton) findViewById(R.id.btncart);

        food_description=(TextView) findViewById(R.id.food_description);
        food_price=(TextView) findViewById(R.id.food_price);
        food_name=(TextView) findViewById(R.id.food_name);

        food_image=(ImageView) findViewById(R.id.img_food);

        collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.Expandedappbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.Collapsedappbar);

        //get food id from Intent
        if(getIntent() != null)
            foodId = getIntent().getStringExtra("FoodId");
        if (!foodId.isEmpty())
        {
            getDetailFood(foodId);
        }

    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                food Food=snapshot.getValue(food.class);

                //set Image
                Picasso.with(getBaseContext()).load(Food.getImage)
                        .into(food_image);

                collapsingToolbarLayout.setTitle(Food.getName());

                food_price.setText(Food.getPrice());

                food_name.setText(Food.getName());

                food_description.setText(Food.getDescription());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}