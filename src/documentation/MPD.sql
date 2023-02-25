CREATE DATABASE IF NOT EXISTS `toutdouxlist` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;


USE `toutdouxlist`;


CREATE TABLE IF NOT EXISTS `Utilisateur` (
    `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
    `nom` varchar(50) NOT NULL,
    `prenom` varchar(50) NOT NULL,
    `login` varchar(100) NOT NULL,
    `mdp` varchar(50) NOT NULL,
    PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Liste` (
    `id_liste` int(11) NOT NULL AUTO_INCREMENT,
    `nom` varchar(50) NOT NULL,
    `description` varchar(50) NOT NULL,
    PRIMARY KEY (`id_liste`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Tache` (
    `id_tache` int(11) NOT NULL AUTO_INCREMENT,
    `nom` varchar(50) NOT NULL,
    `description` varchar(50) NOT NULL,
    `est_realise` tinyint(1) NOT NULL,
    `ref_liste` int(11) NOT NULL,
    `ref_type` int(11) NOT NULL,
    PRIMARY KEY (`id_tache`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Type` (
    `id_type` int(11) NOT NULL AUTO_INCREMENT,
    `libelle` varchar(50) NOT NULL,
    `code_couleur` varchar(50) NOT NULL,
    PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Participe` (
    `ref_utilisateur` int(11) NOT NULL,
    `ref_liste` int(11) NOT NULL,
    PRIMARY KEY (`ref_utilisateur`, `ref_liste`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `Tache`
ADD CONSTRAINT `fk_tache_liste` FOREIGN KEY (`ref_liste`) REFERENCES `Liste` (`id_liste`),
ADD CONSTRAINT `fk_tache_type` FOREIGN KEY (`ref_type`) REFERENCES `Type` (`id_type`);


ALTER TABLE `Participe`
ADD CONSTRAINT `fk_participe_utilisateur` FOREIGN KEY (`ref_utilisateur`) REFERENCES `Utilisateur` (`id_utilisateur`),
ADD CONSTRAINT `fk_participe_liste` FOREIGN KEY (`ref_liste`) REFERENCES `Liste` (`id_liste`);