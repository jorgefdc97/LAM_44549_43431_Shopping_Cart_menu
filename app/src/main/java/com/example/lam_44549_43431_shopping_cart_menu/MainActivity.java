package com.example.lam_44549_43431_shopping_cart_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executorService;
    Handler mainThreadHandler;
    public static RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;
    public static Context context;
    public static DB_Handler db;
    private final String url = "https://hostingalunos.upt.pt/~dam/produtos.txt";
    public static final String OPCAO_SELECCIONADA = "OPCAO_SELECCIONADA" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        db = new DB_Handler(this);
        executorService = Executors.newFixedThreadPool(1);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        //sleeping for 1 second to read url content and write in DB
        db.reconstructDB();
        try {
            new ExecutorTask(executorService,mainThreadHandler,url,db);
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         /*
        MainActivity.db.add_product(new Product(1,"Produto1", 2, 1));
        MainActivity.db.add_product(new Product(2,"Produto2", 2, 1));
        MainActivity.db.add_product(new Product(3,"Produto3", 2, 1));
        MainActivity.db.add_product(new Product(4,"Produto4", 2, 0));
        MainActivity.db.add_product(new Product(5,"Produto5", 2, 0));
        MainActivity.db.add_product(new Product(6,"Produto6", 2, 0));
        MainActivity.db.add_product(new Product(7,"Produto7", 2, 0));
        MainActivity.db.add_product(new Product(8,"Produto8", 2, 0));
        MainActivity.db.add_product(new Product(9,"Produto9", 2, 0));
        MainActivity.db.add_product(new Product(10,"Produto10", 2, 1));

          */
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Product> products = db.get_all_products();
        updateAdapter(products);
        Log.e("PRODUCTS' LIST", products.toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.opcao1:
            case R.id.opcao2:
            case R.id.opcao3:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                intent = new Intent(this,MainActivity2.class);
                intent.putExtra(OPCAO_SELECCIONADA,item.getTitle());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.opcao1 == item.getItemId()){
            onClickShowAll();
        } else {
            if (R.id.opcao2 == item.getItemId()) {
                onClickShowInCart();
            }else{
                onClickShowNotInCart();
            }
        }
        return super.onOptionsItemSelected(item);
    }

     */
    public void onClickShowAll(){
        ArrayList<Product> products = db.get_all_products();
        Log.e("PRODUCTS' LIST", products.toString());
        updateAdapter(products);
    }
    public void onClickShowNotInCart(){
        ArrayList<Product> products = db.getAllProductsNotInCart();
        Log.e("PRODUCTS'NOT IN CART", products.toString());
        updateAdapter(products);
    }
    public void onClickShowInCart(){
        List<Product> products = db.getAllProductsInCart();
        Log.e("PRODUCTS' IN CART", products.toString());
        updateAdapter(products);
    }
    public void updateAdapter(List<Product> products){
        mAdapter = new MyAdapter(products);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}