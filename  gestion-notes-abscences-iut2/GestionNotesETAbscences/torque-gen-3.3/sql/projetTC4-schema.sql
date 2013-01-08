
# -----------------------------------------------------------------------
# publisher
# -----------------------------------------------------------------------
drop table if exists publisher;

CREATE TABLE publisher
(
    publisher_id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY(publisher_id));


# -----------------------------------------------------------------------
# author
# -----------------------------------------------------------------------
drop table if exists author;

CREATE TABLE author
(
    author_id INTEGER NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    PRIMARY KEY(author_id));


# -----------------------------------------------------------------------
# book
# -----------------------------------------------------------------------
drop table if exists book;

CREATE TABLE book
(
    book_id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(24) NOT NULL,
    publisher_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    PRIMARY KEY(book_id));


# -----------------------------------------------------------------------
# reader
# -----------------------------------------------------------------------
drop table if exists reader;

CREATE TABLE reader
(
    reader_id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(reader_id));


# -----------------------------------------------------------------------
# reference
# -----------------------------------------------------------------------
drop table if exists reference;

CREATE TABLE reference
(
    book_id INTEGER NOT NULL,
    reader_id INTEGER NOT NULL,
    test VARCHAR(24),
    PRIMARY KEY(book_id,reader_id));

ALTER TABLE book
    ADD CONSTRAINT book_FK_1
    FOREIGN KEY (publisher_id)
    REFERENCES publisher (publisher_id)
;

ALTER TABLE book
    ADD CONSTRAINT book_FK_2
    FOREIGN KEY (author_id)
    REFERENCES author (author_id)
;

ALTER TABLE reference
    ADD CONSTRAINT reference_FK_1
    FOREIGN KEY (book_id)
    REFERENCES book (book_id)
;

ALTER TABLE reference
    ADD CONSTRAINT reference_FK_2
    FOREIGN KEY (reader_id)
    REFERENCES reader (reader_id)
;

