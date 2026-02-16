package com.tss.Structural.Adapter.adapter;

import com.tss.Structural.Adapter.adaptee.Hat;
import com.tss.Structural.Adapter.model.Item;

public class HatAdapter implements Item {
    Hat hat;
    public HatAdapter(Hat hat){
        this.hat = hat;
    }
    @Override
    public String getItemName() {
        return hat.getShortName() + "-" + hat.getLongName();
    }

    @Override
    public String toString() {
        return "HatAdapter{" +
                "hat=" + hat +
                '}';
    }

    @Override
    public double getItemPrice() {
        double taxAmount = hat.getBasePrice() * hat.getTax() / 100;
        return hat.getBasePrice()+taxAmount;
    }
}
