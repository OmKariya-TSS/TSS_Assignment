package com.tss.Structural.Adapter.test;

import com.tss.Structural.Adapter.model.Buiscuit;
import com.tss.Structural.Adapter.model.Chocolate;
import com.tss.Structural.Adapter.model.Item;
import com.tss.Structural.Adapter.service.Menu;

import java.util.Scanner;


public class ShoppingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to Shopping App");
        Menu.menu();
        Item goodDay = new Buiscuit("GoodDay",20);
        Item kitkat = new Chocolate("Kitkat",50);
        Item MarieGold = new Buiscuit("MarieGold",30);
        Item DairyMilk = new Chocolate("DairyMilk",100);

        while (true) {
            System.out.println("Enter a number :");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    Menu.addItemToCart(goodDay);
                    Menu.addItemToCart(kitkat);
                    Menu.addItemToCart(MarieGold);
                    Menu.addItemToCart(DairyMilk);
                    Menu.menu();
                    break;
                case 2:
                    Menu.removeItemFromCart(goodDay);
                    Menu.removeItemFromCart(kitkat);
                    Menu.removeItemFromCart(MarieGold);
                    Menu.removeItemFromCart(DairyMilk);
                    Menu.menu();
                    break;
                case 3:
                    Menu.displayCart();
                    Menu.menu();
                    break;
                case 4:
                    double gooddayPrice = Menu.GetItemPrice(goodDay);
                    double kitkatPrice = Menu.GetItemPrice(kitkat);
                    double MariePrice = Menu.GetItemPrice(MarieGold);
                    double dairymilkPrice = Menu.GetItemPrice(DairyMilk);
                    System.out.println("Goodday : " + gooddayPrice);
                    System.out.println("Kitkat : " + kitkatPrice);
                    System.out.println("MarieGold : " + MariePrice);
                    System.out.println("dairyMilk : " + dairymilkPrice);
                    Menu.menu();
                    break;
                case 5:
                    double cartPrice = Menu.GetCartPrice();
                    System.out.println("cart price : " + cartPrice);
                    Menu.menu();
                    break;
                case 6:
                    Menu.GetItemInformation(kitkat);
                    Menu.GetItemInformation(MarieGold);
                    Menu.GetItemInformation(DairyMilk);
                    Menu.GetItemInformation(goodDay);
                    Menu.menu();
                    break;
                case 7:
                    Menu.reset();
                    Menu.menu();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("you choose wrong number");
                    Menu.menu();
            }
        }
    }
}
