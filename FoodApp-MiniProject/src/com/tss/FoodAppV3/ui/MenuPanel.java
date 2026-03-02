package com.tss.FoodAppV3.ui;

import com.tss.FoodAppV3.decorator.BestSellerDecorator;
import com.tss.FoodAppV3.decorator.NewItemDecorator;
import com.tss.FoodAppV3.decorator.SpicyDecorator;
import com.tss.FoodAppV3.enums.MenuCategory;
import com.tss.FoodAppV3.exceptions.MenuItemNotFoundException;
import com.tss.FoodAppV3.exceptions.RestaurantNotFoundException;
import com.tss.FoodAppV3.exceptions.ValidationException;
import com.tss.FoodAppV3.model.MenuItem;
import com.tss.FoodAppV3.service.interfaces.IMenuService;
import com.tss.FoodAppV3.singleton.RestaurantRegistry;

public class MenuPanel {

    private final IMenuService menuService;
    private final RestaurantRegistry registry;
    private final InputHelper input;

    public static int menuItemCount ;

    public MenuPanel(IMenuService menuService, RestaurantRegistry registry, InputHelper input) {
        this.menuService = menuService;
        this.registry    = registry;
        this.input       = input;
    }

    public void manageMenuForRestaurant() {
        try {
            int resId = input.readInt("  Enter restaurant ID: ");

            registry.getById(resId)
                    .orElseThrow(() ->
                            new RestaurantNotFoundException("Restaurant not found with ID: " + resId));

            int choice;
            do {
                System.out.println("\n--- 🍽 Menu Management ---");
                System.out.println("  1. View Menu");
                System.out.println("  2. Add Menu Item");
                System.out.println("  3. Remove Menu Item");
                System.out.println("  4. Update Item Price");
                System.out.println("  5. Tag Item (BestSeller/Spicy/New)");
                System.out.println("  6. Back");
                choice = input.readInt("  Enter choice: ");

                switch (choice) {
                    case 1 -> menuService.displayMenu(resId);
                    case 2 -> addMenuItem(resId);
                    case 3 -> removeMenuItem(resId);
                    case 4 -> updateMenuItemPrice(resId);
                    case 5 -> tagMenuItem(resId);
                    case 6 -> System.out.println("↩ Back to Admin Panel");
                    default -> System.out.println("❌ Invalid choice!");
                }
            } while (choice != 6);

        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    private void addMenuItem(int resId) {
        try {
            System.out.println("\n--- ➕ Add Menu Item ---");
            System.out.print("  Item Name    : ");
            String name = input.getScanner().nextLine().trim();

            if (name.isEmpty()) {
                throw new ValidationException("Item name cannot be empty.");
            }

            System.out.println("  Category: 1.STARTER  2.MAIN  3.DESSERT  4.DRINK");
            int catChoice = input.readInt("  Choose: ");

            MenuCategory category = switch (catChoice) {
                case 1 -> MenuCategory.STARTER;
                case 2 -> MenuCategory.MAIN;
                case 3 -> MenuCategory.DESSERT;
                case 4 -> MenuCategory.DRINK;
                default -> {
                    System.out.println("  Invalid, defaulting to MAIN.");
                    yield MenuCategory.MAIN;
                }
            };

            double price = input.readDouble("  Price ₹: ");
            if (price <= 0) {
                throw new ValidationException("Price must be greater than 0.");
            }

            System.out.print("  Description  : ");
            String desc = input.getScanner().nextLine().trim();

            MenuItem item = new MenuItem(menuItemCount++, name, price, category, desc);
            menuService.addItem(resId, item);
            System.out.println("✅ Item '" + name + "' added!");

        } catch (ValidationException e) {
            System.out.println("  ❌ Validation: " + e.getMessage());
            menuItemCount--;
        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
            menuItemCount--;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
            menuItemCount--;
        }
    }

    private void removeMenuItem(int resId) {
        try {
            menuService.displayMenu(resId);
            int itemId = input.readInt("  Enter item ID to remove: ");
            menuService.removeItem(resId, itemId);
            System.out.println("✅ Item removed.");

        } catch (MenuItemNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    private void updateMenuItemPrice(int resId) {
        try {
            menuService.displayMenu(resId);
            int itemId      = input.readInt("  Enter item ID: ");
            double newPrice = input.readDouble("  New price ₹: ");

            if (newPrice <= 0) {
                throw new ValidationException("Price must be greater than 0.");
            }

            menuService.updateItemPrice(resId, itemId, newPrice);
            System.out.println("✅ Price updated.");

        } catch (ValidationException e) {
            System.out.println("  ❌ Validation: " + e.getMessage());
        } catch (MenuItemNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }

    private void tagMenuItem(int resId) {
        try {
            menuService.displayMenu(resId);
            int itemId = input.readInt("  Enter item ID to tag: ");

            MenuItem item = menuService.getMenu(resId).stream()
                    .filter(m -> m.getItemId() == itemId)
                    .findFirst()
                    .orElseThrow(() -> new MenuItemNotFoundException("item not found with ID: " + itemId));

            System.out.println("  1.⭐ BestSeller  2.🌶 Spicy  3.🆕 New");
            int tagChoice = input.readInt("  Choose: ");

            MenuItem decorated = switch (tagChoice) {
                case 1 -> new BestSellerDecorator(item);
                case 2 -> new SpicyDecorator(item);
                case 3 -> new NewItemDecorator(item);
                default -> {
                    System.out.println("  Invalid tag, no changes.");
                    yield item;
                }
            };

            menuService.removeItem(resId, itemId);
            menuService.addItem(resId, decorated);
            System.out.println("✅ Item tagged!");

        } catch (MenuItemNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }
}