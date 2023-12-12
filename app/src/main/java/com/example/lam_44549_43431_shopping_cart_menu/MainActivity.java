package com.example.lam_44549_43431_shopping_cart_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executorService;
    Handler mainThreadHandler;
    public static RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private static MyAdapter mAdapter;
    public static Context context;
    public static DB_Handler db;
    private final String url = "https://hostingalunos.upt.pt/~dam/produtos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        db = new DB_Handler(this);
        executorService = Executors.newFixedThreadPool(1);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        if(db.check_database_existence()){
            db.reconstructDB();
        }
        new ExecutorTask(executorService,mainThreadHandler,url,db);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.all_products == item.getItemId()){
            showAll();
        } else {
            if (R.id.products_bought == item.getItemId()) {
                showInCart();
            }else{
                if(R.id.products_to_buy == item.getItemId()){
                    showNotInCart();
                }else{
                    rebootDatabase();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void showAll(){
        ArrayList<Product> products = db.get_all_products();
        Log.e("PRODUCTS' LIST", products.toString());
        updateAdapter(products);
    }
    public void showNotInCart(){
        ArrayList<Product> products = db.getAllProductsNotInCart();
        Log.e("PRODUCTS'NOT IN CART", products.toString());
        updateAdapter(products);
    }
    public void showInCart(){
        List<Product> products = db.getAllProductsInCart();
        Log.e("PRODUCTS' IN CART", products.toString());
        updateAdapter(products);
    }

    public void rebootDatabase(){
        db.reconstructDB();
        updateAdapter(new ArrayList<>());
        new ExecutorTask(executorService,mainThreadHandler,url,db);
    }

    public static void updateAdapter(List<Product> products){
        mAdapter = new MyAdapter(products);
        recyclerView.setAdapter(mAdapter);

    }
}