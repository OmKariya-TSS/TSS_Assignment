package com.tss.Structural.HomeAssignment.decoratorHomeAssignment.decorators;

import com.tss.Structural.HomeAssignment.decoratorHomeAssignment.model.Hat;

public class RibbonHat extends HatDecorator {
    public RibbonHat(Hat hat){
        super(hat);
    }
    @Override
    public String getName(){
        return super.getName()+"ribbon hat";
    }
    @Override
    public String getColor(){
        return super.getColor()+"ribbon hat color";
    }
    public String getDescription(){
        return super.getDescription()+"ribbon hat description";
    }
}
