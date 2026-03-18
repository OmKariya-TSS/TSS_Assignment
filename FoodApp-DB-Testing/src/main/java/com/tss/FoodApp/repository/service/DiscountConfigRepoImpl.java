package com.tss.FoodApp.repository.service;

import com.tss.FoodApp.config.DBConnection;
import com.tss.FoodApp.repository.interfaces.IDiscountConfigRepo;
import com.tss.FoodApp.singleton.AppConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountConfigRepoImpl implements IDiscountConfigRepo {

    @Override
    public void update() {
        AppConfig config = AppConfig.getInstance();

        String sql = """
        INSERT INTO discount_config
        (config_id, active_type, flat_amount, percentage, threshold, max_agents_per_rest, updated_at)
        VALUES (1, ?::discount_type, ?, ?, ?, ?, NOW())
        ON CONFLICT (config_id)
        DO UPDATE SET
            active_type = EXCLUDED.active_type,
            flat_amount = EXCLUDED.flat_amount,
            percentage = EXCLUDED.percentage,
            threshold = EXCLUDED.threshold,
            max_agents_per_rest = EXCLUDED.max_agents_per_rest,
            updated_at = NOW()
        """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, config.getActiveDiscountType());
            ps.setDouble(2, config.getFlatDiscountAmount());
            ps.setDouble(3, config.getDiscountPercentage());
            ps.setDouble(4, config.getDiscountThreshold());
            ps.setInt(5, config.getMaxAgentsPerRestaurant());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update discount configuration", e);
        }
    }
    @Override
    public void load() {
        String sql = """
            SELECT active_type, flat_amount, percentage, threshold, max_agents_per_rest
            FROM discount_config
            ORDER BY config_id ASC
            LIMIT 1
            """;

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                AppConfig config = AppConfig.getInstance();
                config.setActiveDiscountType(rs.getString("active_type"));
                config.setFlatDiscountAmount(rs.getDouble("flat_amount"));
                config.setDiscountPercentage(rs.getDouble("percentage"));
                config.setDiscountThreshold(rs.getDouble("threshold"));
                config.setMaxAgentsPerRestaurant(rs.getInt("max_agents_per_rest"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to load discount configuration", e);
        }
    }

}