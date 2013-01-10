--------------------Insert des groupes--------------------------------------

INSERT INTO groupe (intitule) VALUES ('MESSI');
INSERT INTO groupe (intitule) VALUES ('MIAM');
INSERT INTO groupe (intitule) VALUES ('SIMO');

--------------------Insert des matieres--------------------------------------

INSERT INTO matiere (intitule) VALUES ('INFO-1');
INSERT INTO matiere (intitule) VALUES ('INFO-2');
INSERT INTO matiere (intitule) VALUES ('INFO-3');
INSERT INTO matiere (intitule) VALUES ('INFO-4');
INSERT INTO matiere (intitule) VALUES ('SIL-1');
INSERT INTO matiere (intitule) VALUES ('SIL-2');
INSERT INTO matiere (intitule) VALUES ('SIL-3');
INSERT INTO matiere (intitule) VALUES ('SIL-4');

--------------------Insert des etudiants--------------------------------------

INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Francis', 'Brunet-Manquat', 1);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Philippe', 'Martin', 1);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Mario', 'Cortes-Cornax', 1);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Françoise', 'Coat', 2);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Laurent', 'Bonnaud', 3);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Sébastien', 'Bourdon', 3);
INSERT INTO etudiant (nom,prenom,groupe_id) VALUES ('Mathieu', 'Gatumel', 2);

--------------------Insert des absences--------------------------------------

INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (1,5,2);
INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (1,1,2);
INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (3,2,3);
INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (2,4,5);
INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (2,4,4);
INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (4,2,6);
INSERT INTO absence (nbheures,etudiant_id,matiere_id) VALUES (5,1,1);

--------------------Insert des notes--------------------------------------

INSERT INTO note (note,etudiant_id,matiere_id) VALUES (0,5,2);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (17,1,2);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (20,2,3);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (4.5,4,5);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (8.9,4,4);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (14,2,6);
INSERT INTO note (note,etudiant_id,matiere_id) VALUES (15,1,1);