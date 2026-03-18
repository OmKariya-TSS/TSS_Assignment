CREATE TYPE order_status    AS ENUM (
    'PLACED', 'CONFIRMED', 'PREPARING',
    'OUT_FOR_DELIVERY', 'DELIVERED', 'CANCELLED'
);
CREATE TYPE payment_method  AS ENUM ('CASH', 'CARD', 'UPI');
CREATE TYPE payment_status  AS ENUM ('PENDING', 'SUCCESS', 'REFUNDED');
CREATE TYPE menu_category   AS ENUM ('STARTER', 'MAIN', 'DESSERT', 'DRINK');
CREATE TYPE discount_type   AS ENUM ('FLAT', 'PERCENTAGE', 'NONE');

CREATE TABLE customers (
    customer_id SERIAL          PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL    CHECK (LENGTH(TRIM(name)) >= 2),
    email       VARCHAR(150)    NOT NULL    UNIQUE,
    password    VARCHAR(255)    NOT NULL    CHECK (LENGTH(password) >= 6),
    phone       VARCHAR(15),
    address     VARCHAR(255)    NOT NULL,
    created_at  TIMESTAMP       NOT NULL    DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL    DEFAULT NOW()
);


CREATE TABLE admins (
    admin_id    SERIAL          PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL    CHECK (LENGTH(TRIM(name)) >= 2),
    email       VARCHAR(150)    NOT NULL    UNIQUE,
    password    VARCHAR(255)    NOT NULL    CHECK (LENGTH(password) >= 6),
    phone       VARCHAR(15),
    admin_code  VARCHAR(100)    NOT NULL    UNIQUE,
    created_at  TIMESTAMP       NOT NULL    DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL    DEFAULT NOW()
);

CREATE TABLE restaurants (
    restaurant_id   SERIAL          PRIMARY KEY,
    name            VARCHAR(150)    NOT NULL    CHECK (LENGTH(TRIM(name)) >= 2),
    location        VARCHAR(255),
    cuisine_type    VARCHAR(100),               
    is_open         BOOLEAN         NOT NULL    DEFAULT TRUE,
    created_at      TIMESTAMP       NOT NULL    DEFAULT NOW(),
    updated_at      TIMESTAMP       NOT NULL    DEFAULT NOW()
);



CREATE TABLE menu_items (
    item_id         SERIAL          PRIMARY KEY,
    restaurant_id   INT             NOT NULL
                        REFERENCES restaurants(restaurant_id) ON DELETE CASCADE,
    name            VARCHAR(150)    NOT NULL    CHECK (LENGTH(TRIM(name)) >= 1),
    price           NUMERIC(10,2)   NOT NULL    CHECK (price > 0),
    category        menu_category   NOT NULL,
    description     TEXT,
    is_available    BOOLEAN         NOT NULL    DEFAULT TRUE,
    is_spicy        BOOLEAN         NOT NULL    DEFAULT FALSE,
    is_best_seller  BOOLEAN         NOT NULL    DEFAULT FALSE,
    is_new          BOOLEAN         NOT NULL    DEFAULT FALSE,
    created_at      TIMESTAMP       NOT NULL    DEFAULT NOW(),
    updated_at      TIMESTAMP       NOT NULL    DEFAULT NOW()
);


CREATE TABLE delivery_agents (
    agent_id            SERIAL          PRIMARY KEY,
    restaurant_id       INT             NOT NULL
                            REFERENCES restaurants(restaurant_id) ON DELETE CASCADE,
    name                VARCHAR(100)    NOT NULL    CHECK (LENGTH(TRIM(name)) >= 2),
    phone               VARCHAR(10)     NOT NULL    UNIQUE
                            CHECK (phone ~ '^\d{10}$'),
    password            VARCHAR(255)    NOT NULL    CHECK (LENGTH(password) >= 6),
    is_available        BOOLEAN         NOT NULL    DEFAULT TRUE,
    total_deliveries    INT             NOT NULL    DEFAULT 0
                            CHECK (total_deliveries >= 0),
    created_at          TIMESTAMP       NOT NULL    DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL    DEFAULT NOW()
);


CREATE TABLE orders (
    order_id            SERIAL              PRIMARY KEY,
    customer_id         INT                 NOT NULL
                            REFERENCES customers(customer_id),
    restaurant_id       INT                 NOT NULL
                            REFERENCES restaurants(restaurant_id),
    agent_id            INT
                            REFERENCES delivery_agents(agent_id) ON DELETE SET NULL,

    status              order_status        NOT NULL    DEFAULT 'PLACED',
    payment_method      payment_method,
    payment_status      payment_status      NOT NULL    DEFAULT 'PENDING',

    subtotal            NUMERIC(10,2)       NOT NULL    DEFAULT 0   CHECK (subtotal >= 0),
    discount_amount     NUMERIC(10,2)       NOT NULL    DEFAULT 0   CHECK (discount_amount >= 0),
    final_total         NUMERIC(10,2)       NOT NULL    DEFAULT 0   CHECK (final_total >= 0),

    special_note        TEXT,
    order_time          TIMESTAMP           NOT NULL    DEFAULT NOW(),
    updated_at          TIMESTAMP           NOT NULL    DEFAULT NOW(),

    CONSTRAINT chk_final_total
        CHECK (final_total = subtotal - discount_amount)
);


CREATE TABLE order_items (
    order_item_id   SERIAL          PRIMARY KEY,
    order_id        INT             NOT NULL
                        REFERENCES orders(order_id) ON DELETE CASCADE,
    item_id         INT                             
                        REFERENCES menu_items(item_id) ON DELETE SET NULL,
    --snapshots 
    item_name       VARCHAR(150)    NOT NULL,
    item_price      NUMERIC(10,2)   NOT NULL    CHECK (item_price > 0),
    quantity        INT             NOT NULL    CHECK (quantity > 0),
    item_total      NUMERIC(10,2)   NOT NULL    CHECK (item_total > 0),

    CONSTRAINT chk_item_total
        CHECK (item_total = item_price * quantity)
);


CREATE TABLE invoices (
    invoice_id          SERIAL          PRIMARY KEY,
    order_id            INT             NOT NULL    UNIQUE
                            REFERENCES orders(order_id) ON DELETE CASCADE,
    subtotal            NUMERIC(10,2)   NOT NULL    CHECK (subtotal >= 0),
    discount_applied    NUMERIC(10,2)   NOT NULL    DEFAULT 0   CHECK (discount_applied >= 0),
    final_amount        NUMERIC(10,2)   NOT NULL    CHECK (final_amount >= 0),
    payment_method      VARCHAR(50),
    generated_at        TIMESTAMP       NOT NULL    DEFAULT NOW()
);


CREATE TABLE discount_config (
    config_id           SERIAL          PRIMARY KEY,
    active_type         discount_type   NOT NULL    DEFAULT 'FLAT',
    flat_amount         NUMERIC(10,2)   NOT NULL    DEFAULT 50.0    CHECK (flat_amount >= 0),
    percentage          NUMERIC(5,2)    NOT NULL    DEFAULT 10.0    CHECK (percentage BETWEEN 0 AND 100),
    threshold           NUMERIC(10,2)   NOT NULL    DEFAULT 500.0   CHECK (threshold >= 0),
    max_agents_per_rest INT             NOT NULL    DEFAULT 5       CHECK (max_agents_per_rest > 0),
    updated_at          TIMESTAMP       NOT NULL    DEFAULT NOW()
);

INSERT INTO discount_config DEFAULT VALUES;


