package com.tss.Structural.HomeAssignment.decoratorHomeAssignment.test;

import com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model.Hat;
import com.tss.Structural.HomeAssignment.decoratorHomeAssignment.decorators.RibbonHat;
import com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model.StandardHat;

public class HatMain {
    public static void main(String[] args) {
        Hat standardHat = new StandardHat("yellow","Std hat","std hat description");
        System.out.println(standardHat.getColor());
        System.out.println(standardHat.getDescription());
        System.out.println(standardHat.getName());
        RibbonHat ribbonHat = new RibbonHat(standardHat);
        System.out.println(ribbonHat.getColor());
    }
}
