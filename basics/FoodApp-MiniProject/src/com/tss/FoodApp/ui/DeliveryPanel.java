package com.tss.FoodApp.ui;

import com.tss.FoodApp.exceptions.*;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.service.interfaces.IDeliveryService;
import com.tss.FoodApp.singleton.RestaurantRegistry;

import java.util.List;

public class DeliveryPanel {

    private final IDeliveryService deliveryService;
    private final RestaurantRegistry registry;
    private final InputHelper input;

    static int deliveryAgentCount = 1;

    public DeliveryPanel(IDeliveryService deliveryService, RestaurantRegistry registry, InputHelper input) {
        this.deliveryService = deliveryService;
        this.registry        = registry;
        this.input           = input;
    }

    public void addDeliveryAgent() {
        try {
            System.out.println("\n--- ➕ Add Delivery Agent ---");
            int resId = input.readInt("  Restaurant ID: ");

            Restaurant restaurant = registry.getById(resId)
                    .orElseThrow(() ->
                            new RestaurantNotFoundException(
                                    "Restaurant not found with ID: " + resId));

            System.out.print("  Agent Name : ");
            String name = input.getScanner().nextLine().trim();

            System.out.print("  Phone      : ");
            String phone = input.getScanner().nextLine().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                throw new ValidationException(
                        "Agent name and phone cannot be empty.");
            }

            if (!phone.matches("\\d{10}")) {
                throw new ValidationException(
                        "Phone must be 10 digits.");
            }

            boolean phoneExists = restaurant.getAgents()
                    .stream()
                    .anyMatch(a -> a.getPhone().equals(phone));

            if (phoneExists) {
                throw new ValidationException(
                        "Delivery agent with phone number "
                                + phone + " already exists.");
            }

            DeliveryAgent agent =
                    new DeliveryAgent(deliveryAgentCount++, name, phone);

            deliveryService.addAgent(resId, agent);

            System.out.println("✅ Agent '" + name +
                    "' added to " + restaurant.getName());

        } catch (ValidationException e) {
            System.out.println("  ❌ Validation: " + e.getMessage());
            deliveryAgentCount--;
        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
            deliveryAgentCount--;
        } catch (AgentNotAvailableException e) {
            System.out.println("  ❌ Agent error: " + e.getMessage());
            deliveryAgentCount--;
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
            deliveryAgentCount--;
        }
    }

    public void viewDeliveryAgents() {
        try {
            int resId = input.readInt("  Enter restaurant ID: ");

            Restaurant restaurant = registry.getById(resId)
                    .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + resId));

            List<DeliveryAgent> agents = restaurant.getAgents();
            if (agents.isEmpty()) {
                System.out.println("⚠ No agents added yet.");
                return;
            }
            System.out.println("\n--- Agents: " + restaurant.getName() + " ---");
            agents.forEach(a ->
                    System.out.printf("  ID:%-3d | %-15s | %s | %s%n",
                            a.getAgentId(), a.getName(), a.getPhone(),
                            a.isAvailable() ? "✅ Available" : "🔴 Busy")
            );

        } catch (RestaurantNotFoundException e) {
            System.out.println("  ❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected error: " + e.getMessage());
        }
    }
}