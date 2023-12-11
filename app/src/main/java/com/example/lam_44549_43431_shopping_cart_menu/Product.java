package com.example.lam_44549_43431_shopping_cart_menu;

public class Product {
    private int id;
    private String description;
    private int quantity;
    private int bought;

    public static int ID_NUM=0;

    public Product(){}
    public Product(String description, int quantity, int bought) {
        this.description = description;
        this.quantity = quantity;
        this.bought = bought;
    }
    public Product(int id,String description, int quantity, int bought) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.bought = bought;
    }
    public Product(String description){
        this.description=description;
        this.quantity = 0;
        this.bought = 0;
        id=ID_NUM++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBought() {
        return this.bought;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", bought=" + bought +
                '}';
    }

    public void setBought(int bought) {
        this.bought = bought;
    }
}
