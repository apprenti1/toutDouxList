
DROP TABLE IF EXISTS liste;
CREATE TABLE IF NOT EXISTS liste (
  id_liste int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(50) NOT NULL,
  description varchar(50) NOT NULL,
   ref_utilisateur int(11) NOT NULL,
  PRIMARY KEY (id_liste)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS participe;
CREATE TABLE IF NOT EXISTS participe (
  ref_utilisateur int(11) NOT NULL,
  ref_liste int(11) NOT NULL,
  PRIMARY KEY (ref_utilisateur,ref_liste)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS tache;
CREATE TABLE IF NOT EXISTS tache (
  id_tache int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(50) NOT NULL,
  description varchar(50) NOT NULL,
  est_realise tinyint(1) NOT NULL,
  ref_liste int(11) NOT NULL,
  ref_type int(11) NOT NULL,
  PRIMARY KEY (id_tache)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS type;
CREATE TABLE IF NOT EXISTS type (
  id_type int(11) NOT NULL AUTO_INCREMENT,
  libelle varchar(50) NOT NULL,
  code_couleur varchar(50) NOT NULL,
  ref_utilisateur int(11) NOT NULL,
  PRIMARY KEY (id_type)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS utilisateur;
CREATE TABLE IF NOT EXISTS utilisateur (
  id_utilisateur int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(50) NOT NULL,
  prenom varchar(50) NOT NULL,
  login varchar(100) NOT NULL,
  mdp varchar(50) NOT NULL,
  PRIMARY KEY (id_utilisateur)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE participe
  ADD CONSTRAINT fk_participe_liste FOREIGN KEY (ref_liste) REFERENCES liste (id_liste) ON DELETE CASCADE,
  ADD CONSTRAINT fk_participe_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES utilisateur (id_utilisateur) ON DELETE CASCADE;

ALTER TABLE tache
    ADD CONSTRAINT fk_tache_liste FOREIGN KEY (ref_liste) REFERENCES liste (id_liste) ON DELETE CASCADE,
  ADD CONSTRAINT fk_tache_type FOREIGN KEY (ref_type) REFERENCES type (id_type) ON DELETE CASCADE;

ALTER TABLE liste
    ADD CONSTRAINT fk_liste_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES utilisateur (id_utilisateur) ON DELETE CASCADE;
ALTER TABLE type
    ADD CONSTRAINT fk_type_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES utilisateur (id_utilisateur) ON DELETE CASCADE;
