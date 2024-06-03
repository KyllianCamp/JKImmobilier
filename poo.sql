-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 03 juin 2024 à 19:36
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `poo`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien`
--

DROP TABLE IF EXISTS `bien`;
CREATE TABLE IF NOT EXISTS `bien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `codePostal` varchar(255) NOT NULL,
  `nbPieces` int(11) NOT NULL,
  `surface` float NOT NULL,
  `description` varchar(1023) NOT NULL,
  `idProprietaire` int(11) NOT NULL,
  `loyer` float NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idProprietaire` (`idProprietaire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `caracteristique`
--

DROP TABLE IF EXISTS `caracteristique`;
CREATE TABLE IF NOT EXISTS `caracteristique` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `caracteristiquebien`
--

DROP TABLE IF EXISTS `caracteristiquebien`;
CREATE TABLE IF NOT EXISTS `caracteristiquebien` (
  `idCaracteristique` int(11) NOT NULL,
  `idBien` int(11) NOT NULL,
  PRIMARY KEY (`idCaracteristique`,`idBien`),
  KEY `idCaracteristique` (`idCaracteristique`,`idBien`),
  KEY `idBien` (`idBien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

DROP TABLE IF EXISTS `dossier`;
CREATE TABLE IF NOT EXISTS `dossier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCandidat` int(11) NOT NULL,
  `idBien` int(11) NOT NULL,
  `dateSoumission` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCandidat` (`idCandidat`),
  KEY `idBien` (`idBien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idLocation` int(11) NOT NULL,
  `numeroFacture` int(11) NOT NULL,
  `prixHT` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idLocation` (`idLocation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBien` int(11) NOT NULL,
  `dateDebut` varchar(255) NOT NULL,
  `dateFin` varchar(255) NOT NULL,
  `commentaire` int(11) NOT NULL,
  `idLocataire` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idLocataire` (`idLocataire`),
  KEY `idBien` (`idBien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idLocation` int(11) NOT NULL,
  `prix` float NOT NULL,
  `etat` varchar(255) NOT NULL,
  `rib` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idLocation` (`idLocation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `photographie`
--

DROP TABLE IF EXISTS `photographie`;
CREATE TABLE IF NOT EXISTS `photographie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBien` int(11) NOT NULL,
  `lien` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBien` (`idBien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `preavis`
--

DROP TABLE IF EXISTS `preavis`;
CREATE TABLE IF NOT EXISTS `preavis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idLocation` int(11) NOT NULL,
  `datePreavis` int(11) NOT NULL,
  `dateDepart` int(11) NOT NULL,
  `motif` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idLocation` (`idLocation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bien`
--
ALTER TABLE `bien`
  ADD CONSTRAINT `bien_ibfk_1` FOREIGN KEY (`idProprietaire`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `caracteristiquebien`
--
ALTER TABLE `caracteristiquebien`
  ADD CONSTRAINT `caracteristiquebien_ibfk_1` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`),
  ADD CONSTRAINT `caracteristiquebien_ibfk_2` FOREIGN KEY (`idCaracteristique`) REFERENCES `caracteristique` (`id`);

--
-- Contraintes pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD CONSTRAINT `dossier_ibfk_1` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `facture_ibfk_1` FOREIGN KEY (`idLocation`) REFERENCES `location` (`id`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`),
  ADD CONSTRAINT `location_ibfk_2` FOREIGN KEY (`idLocataire`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`idLocation`) REFERENCES `location` (`id`);

--
-- Contraintes pour la table `photographie`
--
ALTER TABLE `photographie`
  ADD CONSTRAINT `photographie_ibfk_1` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`);

--
-- Contraintes pour la table `preavis`
--
ALTER TABLE `preavis`
  ADD CONSTRAINT `preavis_ibfk_1` FOREIGN KEY (`idLocation`) REFERENCES `location` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
