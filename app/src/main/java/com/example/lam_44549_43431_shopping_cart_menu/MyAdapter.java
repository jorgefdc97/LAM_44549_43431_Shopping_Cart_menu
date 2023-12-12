package com.example.lam_44549_43431_shopping_cart_menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Product>  mDataset;
    public MyAdapter(List<Product> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_view_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = mDataset.get(position);
        if(product != null) {
            holder.descriptionView.setText(mDataset.get(position).getDescription());
            holder.quantityView.setText(new Integer(mDataset.get(position).getQuantity()).toString());
            holder.boughtBox.setChecked(mDataset.get(position).getBought() != 0);
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


