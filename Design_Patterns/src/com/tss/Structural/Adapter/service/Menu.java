package com.tss.Structural.Adapter.service;

import com.tss.Structural.Adapter.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static List<Item> items = new ArrayList<>();
    public  static ShoppingCart cart = new ShoppingCart(items);
    public static void menu(){
        System.out.println("1 Add item to Cart");
        System.out.println("2: Remove Item from Cart");
        System.out.println("3: Display Cart Items");
        System.out.println("4: Get Item Price");
        System.out.println("5: Get Cart Price");
        System.out.println("6: Get Item information");
        System.out.println("7: Reset the cart");
    }
    public static void addItemToCart(Item item){
        cart.addItemToCart(item);
    }
    public static void removeItemFromCart(Item item){
        cart.removeFromCart(item);
    }
    public static void displayCart(){
        cart.displayCart();
    }
    public static double GetItemPrice(Item item){
        return item.getItemPrice();
    }
    public static double GetCartPrice(){
        return cart.getCartPrice();
    }
    public static void GetItemInformation(Item item){
        System.out.println(item);
    }
    public static void reset(){
        cart.resetCart();
    }

}
