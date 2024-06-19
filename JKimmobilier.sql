-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 19 juin 2024 à 20:23
-- Version du serveur : 5.7.39
-- Version de PHP : 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `JKimmobilier`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien`
--

CREATE TABLE `bien` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `codePostal` varchar(255) NOT NULL,
  `nbPieces` int(11) NOT NULL,
  `surface` float NOT NULL,
  `description` varchar(1023) NOT NULL,
  `idProprietaire` int(11) NOT NULL,
  `loyer` float NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bien`
--

INSERT INTO `bien` (`id`, `nom`, `adresse`, `codePostal`, `nbPieces`, `surface`, `description`, `idProprietaire`, `loyer`, `type`) VALUES
(11, 'Appartement Lens', '53 rue des tulipes', '62300', 2, 23, 'petit appartement', 7, 450, 'Appartement'),
(12, 'Test', 'test', 'Test', 3, 23, 'Test', 7, 2345, 'Test'),
(13, 'test1', 'test1', 'test1', 1, 1, 'test1', 8, 1, 'test1');

-- --------------------------------------------------------

--
-- Structure de la table `caracteristique`
--

CREATE TABLE `caracteristique` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `caracteristique`
--

INSERT INTO `caracteristique` (`id`, `nom`) VALUES
(1, 'piscine'),
(2, 'Jacuzzi'),
(3, 'jardin'),
(4, 'test'),
(5, 'cara');

-- --------------------------------------------------------

--
-- Structure de la table `caracteristiqueBien`
--

CREATE TABLE `caracteristiqueBien` (
  `idCaracteristique` int(11) NOT NULL,
  `idBien` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `caracteristiqueBien`
--

INSERT INTO `caracteristiqueBien` (`idCaracteristique`, `idBien`, `id`) VALUES
(2, 11, 59),
(3, 11, 60),
(4, 11, 61);

-- --------------------------------------------------------

--
-- Structure de la table `dossier`
--

CREATE TABLE `dossier` (
  `id` int(11) NOT NULL,
  `idCandidat` int(11) NOT NULL,
  `idBien` int(11) NOT NULL,
  `dateSoumission` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `dossier`
--

INSERT INTO `dossier` (`id`, `idCandidat`, `idBien`, `dateSoumission`, `statut`) VALUES
(7, 8, 11, '2024-06-18', 'Validé');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `idLocation` int(11) NOT NULL,
  `numeroFacture` int(11) NOT NULL,
  `prixHT` float NOT NULL,
  `idPaiement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `idBien` int(11) NOT NULL,
  `dateDebut` varchar(255) NOT NULL,
  `dateFin` varchar(255) DEFAULT NULL,
  `commentaire` text NOT NULL,
  `idLocataire` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id`, `idBien`, `dateDebut`, `dateFin`, `commentaire`, `idLocataire`) VALUES
(5, 11, '2026-12-05', '', 'hop', 8),
(6, 12, '2024-12-31', '', 'hzdsdz', NULL),
(7, 13, '2024-12-31', '', 'dzaodksqdz', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id` int(11) NOT NULL,
  `idLocation` int(11) NOT NULL,
  `prix` float NOT NULL,
  `etat` varchar(255) NOT NULL,
  `rib` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `photographie`
--

CREATE TABLE `photographie` (
  `id` int(11) NOT NULL,
  `idBien` int(11) NOT NULL,
  `lien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `photographie`
--

INSERT INTO `photographie` (`id`, `idBien`, `lien`) VALUES
(16, 11, 'gettyimages-1357529184-612x612.jpg'),
(17, 12, 'gettyimages-1357529184-612x612.jpg'),
(18, 13, 'gettyimages-1357529184-612x612.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `preavis`
--

CREATE TABLE `preavis` (
  `id` int(11) NOT NULL,
  `idLocation` int(11) NOT NULL,
  `datePreavis` varchar(255) NOT NULL,
  `dateDepart` varchar(255) NOT NULL,
  `motif` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `mail`, `telephone`) VALUES
(7, 'Derrick', 'Donald', 'derrick.donald@gmail.com', '0606060606'),
(8, 'Gibrou', 'Renald', 'renald.gibrou@mail.com', '0505050505');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bien`
--
ALTER TABLE `bien`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProprietaire` (`idProprietaire`);

--
-- Index pour la table `caracteristique`
--
ALTER TABLE `caracteristique`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `caracteristiqueBien`
--
ALTER TABLE `caracteristiqueBien`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCaracteristique` (`idCaracteristique`,`idBien`),
  ADD KEY `idBien` (`idBien`);

--
-- Index pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCandidat` (`idCandidat`),
  ADD KEY `idBien` (`idBien`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idLocation` (`idLocation`),
  ADD KEY `FKf6onw5twbjdbxxn7oc5u3pj51` (`idPaiement`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idLocataire` (`idLocataire`),
  ADD KEY `idBien` (`idBien`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idLocation` (`idLocation`);

--
-- Index pour la table `photographie`
--
ALTER TABLE `photographie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idBien` (`idBien`);

--
-- Index pour la table `preavis`
--
ALTER TABLE `preavis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idLocation` (`idLocation`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bien`
--
ALTER TABLE `bien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `caracteristique`
--
ALTER TABLE `caracteristique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `caracteristiqueBien`
--
ALTER TABLE `caracteristiqueBien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT pour la table `dossier`
--
ALTER TABLE `dossier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `photographie`
--
ALTER TABLE `photographie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `preavis`
--
ALTER TABLE `preavis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bien`
--
ALTER TABLE `bien`
  ADD CONSTRAINT `bien_ibfk_1` FOREIGN KEY (`idProprietaire`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `caracteristiqueBien`
--
ALTER TABLE `caracteristiqueBien`
  ADD CONSTRAINT `FKdv4de85l6klqo9fpt9xyp4s7b` FOREIGN KEY (`idCaracteristique`) REFERENCES `caracteristique` (`id`),
  ADD CONSTRAINT `FKkvlv9waaaa4jpj4qylommmi5n` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`),
  ADD CONSTRAINT `caracteristiquebien_ibfk_1` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`),
  ADD CONSTRAINT `caracteristiquebien_ibfk_2` FOREIGN KEY (`idCaracteristique`) REFERENCES `caracteristique` (`id`);

--
-- Contraintes pour la table `dossier`
--
ALTER TABLE `dossier`
  ADD CONSTRAINT `FKnb2t174xv3c4ofaf3feffnppr` FOREIGN KEY (`idCandidat`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `dossier_ibfk_1` FOREIGN KEY (`idBien`) REFERENCES `bien` (`id`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `FKf6onw5twbjdbxxn7oc5u3pj51` FOREIGN KEY (`idPaiement`) REFERENCES `paiement` (`id`),
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
