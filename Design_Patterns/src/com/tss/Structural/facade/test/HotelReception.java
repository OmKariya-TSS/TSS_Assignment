package com.tss.Structural.facade.test;

import com.tss.Structural.facade.model.Luggage;
import com.tss.Structural.facade.model.Restaurant;
import com.tss.Structural.facade.model.Room;
import com.tss.Structural.facade.service.LuggageService;
import com.tss.Structural.facade.service.RestaurantService;
import com.tss.Structural.facade.service.RoomService;

public class HotelReception {
    public static void main(String[] args) {
        LuggageService luggageService = new LuggageService();
        RestaurantService restaurantService = new RestaurantService();
        RoomService roomService = new RoomService();
        System.out.println("welcome to hotel reception");
        Restaurant om = new Restaurant("om",1);
        Room room = new Room(100,1);
        Room room2 = new Room(200,2);
        Luggage luggage = new Luggage("sportsBag",false,1);
        restaurantService.serveFood(om);
        luggageService.pickLuggage(luggage);
        roomService.cleanRoom(room);
        roomService.cleanRoom(room2);
    }
}
