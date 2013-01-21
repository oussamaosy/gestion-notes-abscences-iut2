#  --------------------Insert des groupes--------------------------------------

INSERT INTO groupe (intitule) VALUES ('MESSI');
INSERT INTO groupe (intitule) VALUES ('MIAM');
INSERT INTO groupe (intitule) VALUES ('SIMO');

#  --------------------Insert des matieres--------------------------------------

INSERT INTO matiere (intitule) VALUES ('INFO-1');
INSERT INTO matiere (intitule) VALUES ('INFO-2');
INSERT INTO matiere (intitule) VALUES ('INFO-3');
INSERT INTO matiere (intitule) VALUES ('INFO-4');
INSERT INTO matiere (intitule) VALUES ('SIL-1');
INSERT INTO matiere (intitule) VALUES ('SIL-2');
INSERT INTO matiere (intitule) VALUES ('SIL-3');
INSERT INTO matiere (intitule) VALUES ('SIL-4');

# --------------------Insert des etudiants--------------------------------------

INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Francis', 'Brunet-Manquat', 1);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Philippe', 'Martin', 1);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Mario', 'Cortes-Cornax', 1);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Françoise', 'Coat', 2);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Laurent', 'Bonnaud', 3);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Sébastien', 'Bourdon', 3);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Mathieu', 'Gatumel', 2);

#  --------------------Insert des absences--------------------------------------

INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (1,'2012-10-4 14:00:00',5,2);
INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (1,'2012-12-16 8:00:00',1,2);
INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (3,'2012-11-5 14:00:00',2,3);
INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (2,'2013-1-4 13:00:00',4,5);
INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (2,'2013-10-4 9:00:00',4,4);
INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (4,'2012-10-4 13:00:00',2,6);
INSERT INTO absence (nbheures,date,etudiant_id,matiere_id) VALUES (5,'2013-1-4 11:00:00',1,1);

# --------------------Insert des notes--------------------------------------

INSERT INTO note (note,etudiant_id,matiere_id) VALUES (0,5,2);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (17,1,2);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (20,2,3);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (4.5,4,5);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (8.9,4,4);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (14,2,6);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (15,1,1);

# --------------------Insert des categories--------------------------------------

INSERT INTO categorie (intitule) VALUES ('administrateur');
INSERT INTO categorie (intitule) VALUES ('abonne');

# --------------------Insert des utilisateurs--------------------------------------

INSERT INTO utilisateur (nom,prenom,login,password,categorie_id) VALUES ('askri','moez,','client','sesame',1);
INSERT INTO utilisateur (nom,prenom,login,password,categorie_id) VALUES ('fournier','colin,','client2','sesame2',2);
