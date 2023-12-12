package com.example.lam_44549_43431_shopping_cart_menu;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView descriptionView;
    public TextView quantityView;
    public CheckBox boughtBox;
    private String description;
    private String quantity;
    private int bought;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        descriptionView = (TextView) itemView.findViewById(R.id.description_textview);
        quantityView = (EditText) itemView.findViewById(R.id.quantity_textview);
        boughtBox = (CheckBox) itemView.findViewById(R.id.bought_checkbox);

        description = descriptionView.getText().toString();
        quantity = quantityView.getText().toString();
        if(boughtBox.isChecked()){
            bought = 1;
        }else{
            bought = 0;
        }

        quantityView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Product product = new Product(descriptionView.getText().toString(), Integer.parseInt(quantityView.getText().toString()), bought);
                    MainActivity.db.update_product(product);
                }
            }
        });

        boughtBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton box, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.context, descriptionView.getText().toString() + " has been added to cart", Toast.LENGTH_SHORT).show();
                    bought = 1;
                }else{
                    Toast.makeText(MainActivity.context, descriptionView.getText().toString() + " has been removed from cart", Toast.LENGTH_SHORT).show();
                    bought = 0;
                }
                Product product = new Product(descriptionView.getText().toString(), Integer.parseInt(quantityView.getText().toString()), bought);
                MainActivity.db.update_product(product);
            }
        });
    }
}