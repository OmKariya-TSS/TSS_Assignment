
CREATE INDEX idx_customers_email        ON customers(email);
CREATE INDEX idx_admins_email           ON admins(email);
CREATE INDEX idx_admins_code            ON admins(admin_code);

CREATE INDEX idx_restaurants_open       ON restaurants(is_open);
CREATE INDEX idx_restaurants_cuisine    ON restaurants(cuisine_type);

CREATE INDEX idx_menu_items_restaurant  ON menu_items(restaurant_id);
CREATE INDEX idx_menu_items_category    ON menu_items(category);
CREATE INDEX idx_menu_items_available   ON menu_items(is_available);

CREATE INDEX idx_agents_restaurant      ON delivery_agents(restaurant_id);
CREATE INDEX idx_agents_available       ON delivery_agents(is_available);

CREATE INDEX idx_orders_customer        ON orders(customer_id);
CREATE INDEX idx_orders_restaurant      ON orders(restaurant_id);
CREATE INDEX idx_orders_agent           ON orders(agent_id);
CREATE INDEX idx_orders_status          ON orders(status);
CREATE INDEX idx_orders_time            ON orders(order_time DESC);

CREATE INDEX idx_order_items_order      ON order_items(order_id);
CREATE INDEX idx_order_items_item       ON order_items(item_id);

CREATE INDEX idx_invoices_order         ON invoices(order_id);




CREATE VIEW v_agent_stats AS
SELECT
    a.agent_id,
    a.name,
    a.phone,
    r.name              AS restaurant,
    a.total_deliveries,
    a.is_available,
    COUNT(o.order_id)   AS active_orders
FROM delivery_agents a
JOIN restaurants r ON r.restaurant_id = a.restaurant_id
LEFT JOIN orders o ON o.agent_id = a.agent_id
    AND o.status = 'OUT_FOR_DELIVERY'
GROUP BY a.agent_id, a.name, a.phone, r.name,
         a.total_deliveries, a.is_available;


CREATE VIEW v_menu_display AS
SELECT
    mi.item_id,
    r.name          AS restaurant,
    mi.name         AS item_name,
    mi.price,
    mi.category,
    mi.description,
    CONCAT_WS(', ',
        CASE WHEN mi.is_spicy        THEN 'Spicy'       END,
        CASE WHEN mi.is_best_seller  THEN 'BestSeller'  END,
        CASE WHEN mi.is_new          THEN 'New'         END
    ) AS tags
FROM menu_items mi
JOIN restaurants r ON r.restaurant_id = mi.restaurant_id
WHERE mi.is_available = TRUE;
