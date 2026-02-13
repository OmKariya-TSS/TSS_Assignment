package com.tss.ocp.test;

import com.tss.ocp.model.*;
import com.tss.ocp.service.SimpleInterestCalculator;

public class AccountTest {

    public static void main(String[] args) {

        FestivalType regular = () -> 7.0;
        FestivalType diwali = () -> 8.0;
        FestivalType holi = () -> 9.0;
        FestivalType christmas = () -> 8.75;
        FestivalType seniorCitizen = () -> 9.5;

        FixedDeposit fd1 = new FixedDeposit(101, "abc", 100000, 2, diwali);

        FixedDeposit fd2 = new FixedDeposit(102, "def", 150000, 3, holi);

        FixedDeposit fd3 = new FixedDeposit(103, "ghi", 200000, 1, seniorCitizen);

        SimpleInterestCalculator calculator = new SimpleInterestCalculator();

        System.out.println(fd1.getName() + " Interest: " + calculator.calculate(fd1));

        System.out.println(fd2.getName() + " Interest: " + calculator.calculate(fd2));

        System.out.println(fd3.getName() + " Interest: " + calculator.calculate(fd3));
    }
}
