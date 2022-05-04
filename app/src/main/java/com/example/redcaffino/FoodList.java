package com.example.redcaffino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.redcaffino.Interface.Itemclicklistener;
import com.example.redcaffino.ViewHolder.FoodViewHolder;
import com.example.redcaffino.ViewHolder.MenuViewHolder;
import com.example.redcaffino.model.Category;
import com.example.redcaffino.model.food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodlist;

    String categoryId="";
    FirebaseRecyclerAdapter<food, FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        database=FirebaseDatabase.getInstance();
        foodlist=database.getReference("Food");

        recyclerView=(RecyclerView) findViewById(R.id.Recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
            categoryId=getIntent().getStringExtra("CategoryId");
           if(!categoryId.isEmpty() && categoryId != null)
        {
            loadlistfood(categoryId);
        }
    }

    private void loadlistfood(String categoryId) {
        adapter=new FirebaseRecyclerAdapter<food, FoodViewHolder>(food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodlist.orderByChild("MenuID").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodViewHolder ViewHolder, food model, int i) {

                ViewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(ViewHolder.food_image);

                final food local=model;
                ViewHolder.setItemClicklistener(new Itemclicklistener(){
                    public void onClick(View view, int position, boolean isLongClick){
                        //start new activity
                        Intent foodDetail=new Intent(FoodList.this, com.example.redcaffino.FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });
            }
        };
        Log.d("TAG",""+adapter.getItemCount());
            recyclerView.setAdapter(adapter);
    }
}