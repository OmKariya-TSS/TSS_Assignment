package com.tss.Structural.facade.service;

import com.tss.Structural.facade.model.Luggage;

public class LuggageService {
    public void pickLuggage(Luggage luggage){
        if(luggage.isDropped()){
            System.out.println("picking luggage");
            luggage.setDropped(false);
        }else{
            System.out.println("cant pick luggage");
        }
    }
    public void DropLuggage(Luggage luggage){
        if(!luggage.isDropped()){
            System.out.println("dropping luggage");
            luggage.setDropped(true);
        }
        else{
            System.out.println("cant drop luggage");
        }
    }
}
