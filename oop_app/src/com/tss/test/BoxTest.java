package com.tss.test;

import com.tss.model.Box;

import java.util.Scanner;

public class BoxTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter values of height width and depth for three boxes");
        System.out.println("enter width for box1");
        double width1 = scanner.nextDouble();
        System.out.println("enter height for box1");
        double height1 = scanner.nextDouble();
        System.out.println("enter depth for box1");
        double depth1 = scanner.nextDouble();
        Box box1 = new Box(width1,height1,depth1);
        System.out.println("enter width for box2");
        double width2 = scanner.nextDouble();
        System.out.println("enter height for box2");
        double height2 = scanner.nextDouble();
        System.out.println("enter depth for box2");
        double depth2 = scanner.nextDouble();
        Box box2 = new Box(width2,height2,depth2);
        System.out.println("enter width for box3");
        double width3 = scanner.nextDouble();
        System.out.println("enter height for box3");
        double height3 = scanner.nextDouble();
        System.out.println("enter depth for box3");
        double depth3 = scanner.nextDouble();
        Box box3 = new Box(width1,height1,depth1);

        System.out.println("box 1 values ae given below :");
        System.out.println("box 1 height is :" + box1.getHeight()+"width is :" + box1.getWidth()+"depth is :" + box1.getDepth());
        System.out.println("box 2 values are given below :");
        System.out.println("box 2 height is :" + box2.getHeight());
        System.out.println("box 2 width is :" + box2.getWidth());
        System.out.println("box 2 depth is :" + box2.getDepth());
        System.out.println("box 3 values are given below");
        System.out.println("box 3 height is :" + box3.getHeight());
        System.out.println("box 3 width is :" + box3.getWidth());
        System.out.println("box 3 depth is :" + box3.getDepth());
        System.out.println("box 1 volume is :"+box1.getVolume(height1,width1,depth1));
        System.out.println("box 2 volume is :"+box2.getVolume(height2,width2,depth2));
        System.out.println("box 3 volume is :"+box3.getVolume(height3,width3,depth3));
    }
}
