CREATE DATABASE adv;
USE adv;

CREATE TABLE photos
(
    id   INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    body BLOB NOT NULL
);

CREATE TABLE advs
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    photo_id   INT          DEFAULT NULL,
    name       TEXT         NOT NULL,
    short_desc VARCHAR(100) NOT NULL,
    long_desc  VARCHAR(500) DEFAULT NULL,
    phone      VARCHAR(12)  NOT NULL,
    price      DECIMAL      NOT NULL,
    FOREIGN KEY advs (photo_id) REFERENCES photos (id)
);
