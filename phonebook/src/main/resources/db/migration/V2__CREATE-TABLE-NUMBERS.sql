CREATE TABLE numbers(
     id SERIAL,
     telephone VARCHAR(50) NOT NULL UNIQUE,
     category VARCHAR(50) NOT NULL,
     contact_id BIGINT,

     CONSTRAINT PK_NUMBERS PRIMARY KEY (id),
     CONSTRAINT FK_CONTACTS FOREIGN KEY (contact_id)
                    REFERENCES contacts (id)
                    ON UPDATE CASCADE
                    ON DELETE CASCADE
);