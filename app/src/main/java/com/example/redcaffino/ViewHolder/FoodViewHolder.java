package com.example.redcaffino.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redcaffino.Interface.Itemclicklistener;
import com.example.redcaffino.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView food_name;
    public ImageView food_image;

    private Itemclicklistener itemclicklistener;

    public void setItemclicklistener(Itemclicklistener itemclicklistener) {
        this.itemclicklistener = itemclicklistener;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        food_name=(TextView)itemView.findViewById(R.id.food_name);
        food_image=(ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);

    }
    public void setItemClicklistener(Itemclicklistener itemclicklistener){
        this.itemclicklistener =itemclicklistener;
    }
    @Override
    public void onClick(View view) {
        itemclicklistener.onClick(view,getAdapterPosition(),false);
    }
}
