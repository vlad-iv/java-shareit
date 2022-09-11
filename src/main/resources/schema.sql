CREATE TABLE IF NOT EXISTS users
(
    user_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255)                            NOT NULL,
    email   VARCHAR(512)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS item_requests
(
    item_request_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    description     VARCHAR(4000)                           NOT NULL,
    requester_id    BIGINT,
    created         TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_item_request PRIMARY KEY (item_request_id),
    CONSTRAINT FK_ITEM_REQUEST_ON_REQUESTER FOREIGN KEY (requester_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS items
(
    item_id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(4000),
    available   BOOLEAN,
    owner_id    BIGINT                                  NOT NULL,
    request_id  BIGINT,
    CONSTRAINT pk_item PRIMARY KEY (item_id),
    CONSTRAINT FK_ITEM_ON_OWNER FOREIGN KEY (owner_id) REFERENCES users (user_id),
    CONSTRAINT FK_ITEM_ON_REQUEST FOREIGN KEY (request_id) REFERENCES item_requests (item_request_id),
    CONSTRAINT UQ_OWNER_ITEM_NAME UNIQUE (owner_id, name)
);

CREATE TABLE IF NOT EXISTS bookings
(
    booking_id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    start_date_time TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    end_date_time   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    item_id         BIGINT,
    booker_id       BIGINT,
    status          VARCHAR(10)                             NOT NULL,
    CONSTRAINT pk_booking PRIMARY KEY (booking_id),
    CONSTRAINT FK_BOOKING_ON_BOOKER FOREIGN KEY (booker_id) REFERENCES users (user_id),
    CONSTRAINT FK_BOOKING_ON_ITEM FOREIGN KEY (item_id) REFERENCES items (item_id)
);

CREATE TABLE IF NOT EXISTS comments
(
    comment_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text       VARCHAR(4000)                           NOT NULL,
    item_id    BIGINT,
    author_id  BIGINT,
    created    TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (comment_id),
    CONSTRAINT FK_COMMENT_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES users (user_id),
    CONSTRAINT FK_COMMENT_ON_ITEM FOREIGN KEY (item_id) REFERENCES items (item_id)
);