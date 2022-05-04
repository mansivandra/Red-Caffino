package com.example.redcaffino.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.redcaffino.Interface.Itemclicklistener;
import com.example.redcaffino.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private Itemclicklistener itemclicklistener;


    public MenuViewHolder( View itemView) {
        super(itemView);

        txtMenuName=(TextView)itemView.findViewById(R.id.menu_name);
        imageView=(ImageView)itemView.findViewById(R.id.menu_image);

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
