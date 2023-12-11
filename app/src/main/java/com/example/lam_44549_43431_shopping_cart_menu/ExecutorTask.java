package com.example.lam_44549_43431_shopping_cart_menu;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ExecutorTask {
    ExecutorService executor;

    Handler resultHandler;

    DB_Handler db;

    public ExecutorTask(ExecutorService executor, Handler resultHandler, String url, DB_Handler db) {
        this.executor = executor;
        this.resultHandler = resultHandler;
        this.db = db;
        this.executor.execute(new Runnable(){
            @Override
            public void run() {
                doWork(url);
            }
        });
    }
    public void doWork(String url) {
        db.reconstructDB();

        HttpHandler handler = new HttpHandler();
        handler.getProducts(url);
        /*
        MainActivity.db.add_product(new Product("Produto1", 2, 1));
        MainActivity.db.add_product(new Product("Produto2", 2, 1));
        MainActivity.db.add_product(new Product("Produto3", 2, 1));
        MainActivity.db.add_product(new Product("Produto4", 2, 0));
        MainActivity.db.add_product(new Product("Produto5", 2, 0));
        MainActivity.db.add_product(new Product("Produto6", 2, 0));
        MainActivity.db.add_product(new Product("Produto7", 2, 0));
        MainActivity.db.add_product(new Product("Produto8", 2, 0));
        MainActivity.db.add_product(new Product("Produto9", 2, 0));
        MainActivity.db.add_product(new Product("Produto10", 2, 1));
        */
    }
}
