package com.tss.Structural.HomeAssignment.decoratorHomeAssignment.decorators;

import com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model.Hat;

public abstract class HatDecorator implements Hat {
    Hat hat ;
    public HatDecorator(Hat hat){
        this.hat = hat;
    }
    @Override
    public String getName(){
        return hat.getName();
    }
    @Override
    public String getColor(){
        return hat.getColor();
    }
    @Override
    public String getDescription(){
        return hat.getDescription();
    }
}
