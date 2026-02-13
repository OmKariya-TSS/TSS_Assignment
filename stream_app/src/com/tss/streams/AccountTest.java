package com.tss.streams;

import com.tss.streams.model.Account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AccountTest {
    public static void main(String[] args) {
        List<Account> list = new ArrayList<>();
        list.add(new Account("OM",50000));
        list.add(new Account("Vivek",10000));
        list.add(new Account("kishan",30000));
        list.add(new Account("neel",40000));
        list.add(new Account("abc",1000));
        list.add(new Account("abcdefgh",100));

        System.out.println("accounts with min balance");
        Optional<Account> acc = list.stream().sorted(Comparator.comparing(Account::getBalance)).findFirst();
        if(acc.isEmpty()){
            System.out.println("not found");
        }
        System.out.println(acc.get());
        System.out.println("accounts with max balance");
        Optional<Account> acc2 = list.stream().sorted(Comparator.comparing(Account::getBalance).reversed()).findFirst();
        System.out.println(acc2);
        System.out.println("names greater than 6");
        list.stream().map(Account::getName).filter((name)->name.length()>6).forEach(System.out::println);
        System.out.println("sum of balance");
//        Double totalBalance = list.stream().map(Account::getBalance).reduce(0.0, Double::sum);
        Double totalBalance = list.stream().map(Account::getBalance).reduce(0.0, (a,b)->a+b);

        //  double sum = list.stream().mapToDouble(Account::getBalance).sum();
        System.out.println(totalBalance);
    }
}
