CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_customers_updated_at
    BEFORE UPDATE ON customers
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_admins_updated_at
    BEFORE UPDATE ON admins
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_restaurants_updated_at
    BEFORE UPDATE ON restaurants
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_menu_items_updated_at
    BEFORE UPDATE ON menu_items
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_agents_updated_at
    BEFORE UPDATE ON delivery_agents
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_orders_updated_at
    BEFORE UPDATE ON orders
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_discount_config_updated_at
    BEFORE UPDATE ON discount_config
    FOR EACH ROW EXECUTE FUNCTION set_updated_at();

CREATE OR REPLACE FUNCTION check_agent_limit()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
    agent_count INT;
    max_agents INT;
BEGIN
    SELECT COUNT(*) INTO agent_count
    FROM delivery_agents
    WHERE restaurant_id = NEW.restaurant_id;

    SELECT max_agents_per_rest INTO max_agents
    FROM discount_config
    LIMIT 1;

    IF agent_count >= max_agents THEN
        RAISE EXCEPTION
        'Maximum delivery agents reached for this restaurant (% allowed)', max_agents;
    END IF;

    RETURN NEW;

END;
$$;

CREATE TRIGGER trg_check_agent_limit
BEFORE INSERT ON delivery_agents
FOR EACH ROW
EXECUTE FUNCTION check_agent_limit();

