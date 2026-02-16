package com.tss.Structural.HomeAssignment.decoratorHomeAssignment.decorators;

import com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model.Hat;

public class GoldenHat extends HatDecorator {
    public GoldenHat(Hat hat){
        super(hat);
    }
    @Override
    public String getColor(){
        return "golden color "+ super.getColor();
    }
    @Override
    public String getName(){
        return super.getName() + "golden hat";
    }
    public String getDescription(){
        return super.getDescription()+"golden hat description";
    }
}
