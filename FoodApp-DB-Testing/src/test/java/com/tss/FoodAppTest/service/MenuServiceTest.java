package com.tss.FoodAppTest.service;

import com.tss.FoodApp.enums.MenuCategory;
import com.tss.FoodApp.exceptions.InvalidMenuItemException;
import com.tss.FoodApp.exceptions.RestaurantNotFoundException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.MenuItem;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IMenuItemRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.repository.service.RestaurantRepositoryImpl;
import com.tss.FoodApp.service.implementations.MenuServiceImpl;
import com.tss.FoodApp.service.interfaces.IMenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuServiceTest {
    @Mock
    private IRestaurantRepository restaurantRepository;
    @Mock
    private IMenuItemRepository menuItemRepository;
    private IMenuService menuService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        menuService = new MenuServiceImpl(restaurantRepository,menuItemRepository);
    }

    @Test
    void testAddItem(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        MenuItem menuItem = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(menuItem,1)).thenReturn(menuItem);
        menuService.addItem(1,menuItem);
        verify(menuItemRepository).save(menuItem,1);
    }

    @Test
    void testAddItem_invalidRestaurantId() {
        MenuItem item = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        assertThrows(IllegalArgumentException.class,
                () -> menuService.addItem(0, item));
    }

    @Test
    void testAddItem_restaurantNotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RestaurantNotFoundException.class,
                () -> menuService.addItem(1, new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr")));
    }

    @Test
    void testRemoveItem_success(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        menuItems.add(menuItem);
        restaurant.setMenu(menuItems);
        when(menuItemRepository.save(menuItem,1)).thenReturn(menuItem);
        menuService.addItem(1,menuItem);
        menuService.removeItem(1,1);
        verify(menuItemRepository).delete(1);
        verify(restaurantRepository).update(restaurant);
    }

    @Test
    void testRemoveItem_invalidIds() {
        assertThrows(IllegalArgumentException.class,
                () -> menuService.removeItem(0, 0));
    }

    @Test
    void testUpdateItemPrice_itemNotFound() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findById(10)).thenReturn(Optional.empty());
        assertThrows(InvalidMenuItemException.class,
                () -> menuService.updateItemPrice(1, 10, 100));
    }

    @Test
    void testUpdateItemPrice(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        menuItems.add(menuItem);
        restaurant.setMenu(menuItems);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(menuItem,1)).thenReturn(menuItem);
        menuService.addItem(1,menuItem);
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(menuItem));
        menuService.updateItemPrice(1,1,2000);
        assertEquals(2000,menuItems.get(0).getPrice());
    }

    @Test
    void testGetMenu_success(){
        menuService.getMenu(1);
        verify(menuItemRepository).findByRestaurant(anyInt());
    }

    @Test
    void testGetMenu_invalidId() {
        assertThrows(IllegalArgumentException.class,
                () -> menuService.getMenu(0));
    }

    @Test
    void testUpdateItemTags_success() {
        MenuItem item = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        menuService.updateItemTags(1, item);
        verify(menuItemRepository).update(item);
    }

    @Test
    void testTagItem(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem item = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        menuItems.add(item);
        restaurant.setMenu(menuItems);
        menuService.updateItemTags(1,item);
        verify(menuItemRepository).update(item);
    }

}
