package com.tss.Structural.Adapter.service;

import com.tss.Structural.Adapter.model.Item;

import java.util.List;

public class ShoppingCart {
    private List<Item> list;

    public ShoppingCart(List<Item> list) {
        this.list = list;
    }

    public void addItemToCart(Item item){
        list.add(item);
    }
    public void removeFromCart(Item item){
        list.remove(item);
    }
    public void resetCart(){
        list.clear();
    }
    public double getCartPrice(){
        double total=0;
        for(Item i : list){
            total = total+i.getItemPrice();
        }
        return total;
    }
    public void displayCart(){
        for(Item i : list){
            System.out.println(i);
        }
        System.out.println("total :" + getCartPrice());
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}
