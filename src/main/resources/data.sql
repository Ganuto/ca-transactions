--SELECT 1;

CREATE TABLE ACCOUNT(
    id BIGINT NOT NULL PRIMARY KEY,
    document_number BIGINT NOT NULL UNIQUE
);

CREATE TABLE OPERATION_TYPE(
    id BIGINT NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE TRANSACTION(
    id BIGINT NOT NULL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    operation_type_id BIGINT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    event_date DATETIME NOT NULL,
    foreign key (account_id) references ACCOUNT(id),
    foreign key (operation_type_id) references OPERATION_TYPE(id)
);