package com.tss.streams;

import com.tss.streams.model.Order;
import com.tss.streams.model.Status;

import java.util.*;
import java.util.stream.Collectors;

public class OrderTest {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Jayesh","Sports",10,200, Status.PENDING));
        orders.add(new Order("Amit", "Electronics", 2, 45000, Status.SHIPPED));
        orders.add(new Order("Neha", "Fashion", 5, 1500, Status.DELIVERED));
        orders.add(new Order("Rohit", "Groceries", 20, 300, Status.PENDING));
        orders.add(new Order("Priya", "Books", 3, 1200, Status.DELIVERED));
        orders.add(new Order("Karan", "Sports", 1, 2500, Status.SHIPPED));
        orders.add(new Order("Sneha", "Beauty", 4, 2200, Status.PENDING));
        orders.add(new Order("Arjun", "Electronics", 1, 65000, Status.PENDING));
        orders.add(new Order("Meena", "Kitchen", 6, 1800, Status.DELIVERED));
        orders.add(new Order("Vikram", "Automobile", 2, 12000, Status.SHIPPED));
        orders.add(new Order("Vikram", "Clothing", 5, 12000, Status.SHIPPED));
        orders.add(new Order("abcd", "Clothing", 8, 123000, Status.SHIPPED));
        orders.add(new Order("abcd", "Clothing", 50, 123000, Status.SHIPPED));

        System.out.println("jayesh orders");
        orders.stream()
                .filter(o -> o.getCustomerName().equals("Jayesh"))
                .forEach(System.out::println);
        System.out.println("delivered orders");
        orders.stream()
                .filter(o-> o.getStatus() == Status.DELIVERED)
                .forEach(System.out::println);
        System.out.println("categories withut duplicates");
        orders.stream()
                .distinct()
                .map(Order::getProductCategory)
                .forEach(System.out::println);
        System.out.println("calculate total revenue");
        double revenue = orders.stream()
                        .filter(o -> o.getStatus() == Status.SHIPPED)
                        .mapToDouble(o -> o.getQuantity() * o.getPricePerUnit())
                        .sum();
        System.out.println("Shipped revenue = " + revenue);
        System.out.println("order for electronic quantiy average");
        double avgQty = orders.stream()
                        .filter(o -> o.getProductCategory().equalsIgnoreCase("Electronics"))
                        .mapToInt(o -> o.getQuantity())
                        .average()
                        .orElse(0.0);
        System.out.println(avgQty);
        System.out.println("order with highest total revenue");
        orders.stream()
                .sorted(Comparator.comparing(o -> o.getQuantity() * o.getPricePerUnit(), Comparator.reverseOrder()))
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println("orders with count group by status");
        Map<Status, List<Order>> ordersByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));

        ordersByStatus.forEach((status, orderList) -> {
            System.out.println("Status: " + status);
            System.out.println("Count: " + orderList.size());

            orderList.forEach(System.out::println);
            System.out.println("--------------------------------");
        });

        System.out.println("customers with more than 3 orders");
        orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 2)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
        System.out.println("first 3 descending total value orders");
        orders.stream()
                .sorted(Comparator.comparing(o -> o.getQuantity() * o.getPricePerUnit(), Comparator.reverseOrder()))
                .limit(3)
                .forEach(System.out::println);
        System.out.println("check if clothing has quantity less than 10");
        boolean check = orders.stream()
                .filter(o->o.getProductCategory()
                        .equalsIgnoreCase("Clothing"))
                .allMatch(o->o.getQuantity()<10);
        System.out.println(check);
    }
}
