-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 01 mars 2023 à 16:37
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `toutdouxlist`
--

-- --------------------------------------------------------

--
-- Structure de la table `liste`
--

DROP TABLE IF EXISTS `liste`;
CREATE TABLE IF NOT EXISTS `liste` (
  `id_liste` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id_liste`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `liste`
--

INSERT INTO `liste` (`id_liste`, `nom`, `description`) VALUES
(1, 'dfegtrhdg', 'ezrtysurutg'),
(2, 'courses', 'manger');

-- --------------------------------------------------------

--
-- Structure de la table `participe`
--

DROP TABLE IF EXISTS `participe`;
CREATE TABLE IF NOT EXISTS `participe` (
  `ref_utilisateur` int(11) NOT NULL,
  `ref_liste` int(11) NOT NULL,
  PRIMARY KEY (`ref_utilisateur`,`ref_liste`),
  KEY `fk_participe_liste` (`ref_liste`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

DROP TABLE IF EXISTS `tache`;
CREATE TABLE IF NOT EXISTS `tache` (
  `id_tache` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `est_realise` tinyint(1) NOT NULL,
  `ref_liste` int(11) NOT NULL,
  `ref_type` int(11) NOT NULL,
  PRIMARY KEY (`id_tache`),
  KEY `fk_tache_liste` (`ref_liste`),
  KEY `fk_tache_type` (`ref_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `code_couleur` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `login` varchar(100) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `login`, `mdp`) VALUES
(1, 'Zins', 'Hugo', 'mail', 'mdp');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `participe`
--
-- ...
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `participe`
--
ALTER TABLE `participe`
    ADD CONSTRAINT `fk_participe_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`) ON DELETE CASCADE,
    ADD CONSTRAINT `fk_participe_utilisateur` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
    ADD CONSTRAINT `fk_tache_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`) ON DELETE CASCADE,
    ADD CONSTRAINT `fk_tache_type` FOREIGN KEY (`ref_type`) REFERENCES `type` (`id_type`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
