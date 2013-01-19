
# -----------------------------------------------------------------------
# groupe
# -----------------------------------------------------------------------
drop table if exists groupe;

CREATE TABLE groupe
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    intitule VARCHAR(128) NOT NULL,
    PRIMARY KEY(id));


# -----------------------------------------------------------------------
# etudiant
# -----------------------------------------------------------------------
drop table if exists etudiant;

CREATE TABLE etudiant
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    nom VARCHAR(128) NOT NULL,
    prenom VARCHAR(128) NOT NULL,
    groupe_id INTEGER,
    PRIMARY KEY(id));


# -----------------------------------------------------------------------
# matiere
# -----------------------------------------------------------------------
drop table if exists matiere;

CREATE TABLE matiere
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    intitule VARCHAR(128) NOT NULL,
    PRIMARY KEY(id));


# -----------------------------------------------------------------------
# absence
# -----------------------------------------------------------------------
drop table if exists absence;

CREATE TABLE absence
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    nbheures INTEGER NOT NULL,
    date DATETIME NOT NULL,
    etudiant_id INTEGER,
    matiere_id INTEGER,
    PRIMARY KEY(id));


# -----------------------------------------------------------------------
# note
# -----------------------------------------------------------------------
drop table if exists note;

CREATE TABLE note
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    note FLOAT NOT NULL,
    etudiant_id INTEGER,
    matiere_id INTEGER,
    PRIMARY KEY(id));

ALTER TABLE etudiant
    ADD CONSTRAINT etudiant_FK_1
    FOREIGN KEY (groupe_id)
    REFERENCES groupe (id)
;

ALTER TABLE absence
    ADD CONSTRAINT absence_FK_1
    FOREIGN KEY (etudiant_id)
    REFERENCES etudiant (id)
;

ALTER TABLE absence
    ADD CONSTRAINT absence_FK_2
    FOREIGN KEY (matiere_id)
    REFERENCES matiere (id)
;

ALTER TABLE note
    ADD CONSTRAINT note_FK_1
    FOREIGN KEY (etudiant_id)
    REFERENCES etudiant (id)
;

ALTER TABLE note
    ADD CONSTRAINT note_FK_2
    FOREIGN KEY (matiere_id)
    REFERENCES matiere (id)
;

