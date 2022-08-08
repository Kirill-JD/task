CREATE TABLE usr
(
    id            NUMBER PRIMARY KEY,
    username      VARCHAR2(255) NOT NULL UNIQUE,
    password      VARCHAR2(255) NOT NULL,
    active        BOOLEAN NOT NULL
);

CREATE SEQUENCE sq_usr_id START WITH 1 INCREMENT BY 1;

CREATE TABLE animal
(
    id              NUMBER PRIMARY KEY,
    name            VARCHAR2(255) NOT NULL UNIQUE,
    gender          VARCHAR2(255) NOT NULL,
    birthdate       VARCHAR(255)  NOT NULL,
    type_animal     VARCHAR2(10)  NOT NULL,
    usr             VARCHAR(255)
);

CREATE SEQUENCE sq_animal_id START WITH 1 INCREMENT BY 1;
