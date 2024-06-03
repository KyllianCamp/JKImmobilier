-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : lun. 03 juin 2024 à 09:55
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
-- Structure de la table `addresses`
--

CREATE TABLE `addresses` (
  `idAddress` int(11) NOT NULL,
  `idProperty` int(11) NOT NULL,
  `city` varchar(255) NOT NULL,
  `postalCode` int(11) NOT NULL,
  `streetName` varchar(255) NOT NULL,
  `numStreet` int(11) NOT NULL,
  `moreInfo` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `burden`
--

CREATE TABLE `burden` (
  `idBurden` int(11) NOT NULL,
  `idRent` int(11) NOT NULL,
  `amount` float NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `burden`
--

INSERT INTO `burden` (`idBurden`, `idRent`, `amount`, `type`, `description`) VALUES
(1, 1, 12, 'poubelle', 'poubelle');

-- --------------------------------------------------------

--
-- Structure de la table `elements`
--

CREATE TABLE `elements` (
  `idElement` int(11) NOT NULL,
  `idRoom` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `direction` varchar(255) NOT NULL,
  `trapPresence` tinyint(1) NOT NULL,
  `numWall` int(11) NOT NULL,
  `nbWindows` int(11) NOT NULL,
  `nbDoors` int(11) NOT NULL,
  `nbHeater` int(11) NOT NULL,
  `nbHeaters` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `furnitures`
--

CREATE TABLE `furnitures` (
  `idFurniture` int(11) NOT NULL,
  `idRoom` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `inventory`
--

CREATE TABLE `inventory` (
  `idInventory` int(11) NOT NULL,
  `idLease` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `invoice`
--

CREATE TABLE `invoice` (
  `idInvoice` int(11) NOT NULL,
  `numInvoice` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lease`
--

CREATE TABLE `lease` (
  `idLease` int(11) NOT NULL,
  `idAgent` int(11) NOT NULL,
  `numLease` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `idTenant` int(11) NOT NULL,
  `idOwner` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ownerproperty`
--

CREATE TABLE `ownerproperty` (
  `idLink` int(11) NOT NULL,
  `idOwner` int(11) NOT NULL,
  `idProperty` int(11) NOT NULL,
  `current` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

CREATE TABLE `payment` (
  `idPayment` int(11) NOT NULL,
  `idRent` int(11) NOT NULL,
  `idTiers` int(11) NOT NULL,
  `rib` varchar(255) NOT NULL,
  `amount` float NOT NULL,
  `date` varchar(255) NOT NULL,
  `dateFor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `properties`
--

CREATE TABLE `properties` (
  `idProperty` int(11) NOT NULL,
  `creationDate` varchar(255) NOT NULL,
  `surface` float NOT NULL,
  `nbRooms` int(11) NOT NULL,
  `idAddress` int(11) DEFAULT NULL,
  `idProperties` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `properties`
--

INSERT INTO `properties` (`idProperty`, `creationDate`, `surface`, `nbRooms`, `idAddress`, `idProperties`) VALUES
(1, '2023-01-01', 12, 1, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `rent`
--

CREATE TABLE `rent` (
  `idRent` int(11) NOT NULL,
  `idProperty` int(11) NOT NULL,
  `amount` float NOT NULL,
  `burdenIncluded` tinyint(1) NOT NULL,
  `date` varchar(255) NOT NULL,
  `dateFor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rent`
--

INSERT INTO `rent` (`idRent`, `idProperty`, `amount`, `burdenIncluded`, `date`, `dateFor`) VALUES
(1, 1, 123, 0, '2023-01-01', '2023-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `rooms`
--

CREATE TABLE `rooms` (
  `idRoom` int(11) NOT NULL,
  `idProperty` int(11) NOT NULL,
  `description` varchar(1023) NOT NULL,
  `surface` float NOT NULL,
  `nbWall` int(11) NOT NULL,
  `nbDoors` int(11) NOT NULL,
  `nbWindows` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stateelement`
--

CREATE TABLE `stateelement` (
  `idStateElement` int(11) NOT NULL,
  `idElement` int(11) NOT NULL,
  `idInventory` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `hour` varchar(255) NOT NULL,
  `description` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stateroom`
--

CREATE TABLE `stateroom` (
  `idStateRoom` int(11) NOT NULL,
  `idRoom` int(11) NOT NULL,
  `idInventory` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `hour` varchar(255) NOT NULL,
  `description` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tiers`
--

CREATE TABLE `tiers` (
  `idTiers` int(11) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `rib` varchar(255) NOT NULL,
  `iban` varchar(255) NOT NULL,
  `bic` varchar(255) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tiers`
--

INSERT INTO `tiers` (`idTiers`, `lastName`, `firstName`, `dateOfBirth`, `rib`, `iban`, `bic`, `mail`, `phone`) VALUES
(1, 'Michka', 'Michka', '1999-01-01', 'test', 'test', 'test', 'test', 'test'),
(2, 'Michka', 'Michka', '1999-01-01', 'test', 'test', 'test', 'test', 'test'),
(3, 'Michka', 'Michka', '1999-01-01', 'test', 'test', 'test', 'test', 'test'),
(4, 'Michka', 'Michka', '1999-01-01', 'test', 'test', 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `wallelements`
--

CREATE TABLE `wallelements` (
  `idWallElement` int(11) NOT NULL,
  `idElement` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`idAddress`),
  ADD KEY `idProperty` (`idProperty`);

--
-- Index pour la table `burden`
--
ALTER TABLE `burden`
  ADD PRIMARY KEY (`idBurden`),
  ADD KEY `idRent` (`idRent`);

--
-- Index pour la table `elements`
--
ALTER TABLE `elements`
  ADD PRIMARY KEY (`idElement`),
  ADD KEY `idRoom` (`idRoom`);

--
-- Index pour la table `furnitures`
--
ALTER TABLE `furnitures`
  ADD PRIMARY KEY (`idFurniture`),
  ADD KEY `idRoom` (`idRoom`);

--
-- Index pour la table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`idInventory`,`idLease`),
  ADD KEY `idInventory` (`idInventory`,`idLease`),
  ADD KEY `idLease` (`idLease`);

--
-- Index pour la table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`idInvoice`);

--
-- Index pour la table `lease`
--
ALTER TABLE `lease`
  ADD PRIMARY KEY (`idLease`),
  ADD KEY `idAgent` (`idAgent`),
  ADD KEY `idTenant` (`idTenant`),
  ADD KEY `idOwner` (`idOwner`);

--
-- Index pour la table `ownerproperty`
--
ALTER TABLE `ownerproperty`
  ADD PRIMARY KEY (`idLink`),
  ADD KEY `idOwner` (`idOwner`,`idProperty`),
  ADD KEY `idProperty` (`idProperty`);

--
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`idPayment`),
  ADD KEY `idTiers` (`idTiers`),
  ADD KEY `idRent` (`idRent`);

--
-- Index pour la table `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`idProperty`),
  ADD KEY `FK568hb1aw2a3dmwiohrfq70let` (`idAddress`);

--
-- Index pour la table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`idRent`),
  ADD KEY `idProperty` (`idProperty`);

--
-- Index pour la table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`idRoom`),
  ADD KEY `idProperty` (`idProperty`);

--
-- Index pour la table `stateelement`
--
ALTER TABLE `stateelement`
  ADD PRIMARY KEY (`idStateElement`),
  ADD KEY `idElement` (`idElement`,`idInventory`),
  ADD KEY `idInventory` (`idInventory`);

--
-- Index pour la table `stateroom`
--
ALTER TABLE `stateroom`
  ADD PRIMARY KEY (`idStateRoom`),
  ADD KEY `idRoom` (`idRoom`,`idInventory`),
  ADD KEY `idInventory` (`idInventory`);

--
-- Index pour la table `tiers`
--
ALTER TABLE `tiers`
  ADD PRIMARY KEY (`idTiers`);

--
-- Index pour la table `wallelements`
--
ALTER TABLE `wallelements`
  ADD PRIMARY KEY (`idWallElement`),
  ADD KEY `idElement` (`idElement`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `idAddress` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `burden`
--
ALTER TABLE `burden`
  MODIFY `idBurden` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `elements`
--
ALTER TABLE `elements`
  MODIFY `idElement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `furnitures`
--
ALTER TABLE `furnitures`
  MODIFY `idFurniture` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `idInvoice` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `lease`
--
ALTER TABLE `lease`
  MODIFY `idLease` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ownerproperty`
--
ALTER TABLE `ownerproperty`
  MODIFY `idLink` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `idPayment` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `properties`
--
ALTER TABLE `properties`
  MODIFY `idProperty` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `rent`
--
ALTER TABLE `rent`
  MODIFY `idRent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `idRoom` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `stateelement`
--
ALTER TABLE `stateelement`
  MODIFY `idStateElement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `stateroom`
--
ALTER TABLE `stateroom`
  MODIFY `idStateRoom` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `tiers`
--
ALTER TABLE `tiers`
  MODIFY `idTiers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `wallelements`
--
ALTER TABLE `wallelements`
  MODIFY `idWallElement` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`idProperty`) REFERENCES `properties` (`idProperty`);

--
-- Contraintes pour la table `burden`
--
ALTER TABLE `burden`
  ADD CONSTRAINT `burden_ibfk_1` FOREIGN KEY (`idRent`) REFERENCES `rent` (`idRent`);

--
-- Contraintes pour la table `elements`
--
ALTER TABLE `elements`
  ADD CONSTRAINT `elements_ibfk_1` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`idRoom`);

--
-- Contraintes pour la table `furnitures`
--
ALTER TABLE `furnitures`
  ADD CONSTRAINT `furnitures_ibfk_1` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`idRoom`);

--
-- Contraintes pour la table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`idLease`) REFERENCES `lease` (`idLease`);

--
-- Contraintes pour la table `lease`
--
ALTER TABLE `lease`
  ADD CONSTRAINT `lease_ibfk_1` FOREIGN KEY (`idAgent`) REFERENCES `tiers` (`idTiers`),
  ADD CONSTRAINT `lease_ibfk_2` FOREIGN KEY (`idTenant`) REFERENCES `tiers` (`idTiers`),
  ADD CONSTRAINT `lease_ibfk_3` FOREIGN KEY (`idOwner`) REFERENCES `tiers` (`idTiers`);

--
-- Contraintes pour la table `ownerproperty`
--
ALTER TABLE `ownerproperty`
  ADD CONSTRAINT `ownerproperty_ibfk_1` FOREIGN KEY (`idProperty`) REFERENCES `properties` (`idProperty`),
  ADD CONSTRAINT `ownerproperty_ibfk_2` FOREIGN KEY (`idOwner`) REFERENCES `tiers` (`idTiers`);

--
-- Contraintes pour la table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`idTiers`) REFERENCES `tiers` (`idTiers`),
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`idRent`) REFERENCES `rent` (`idRent`);

--
-- Contraintes pour la table `properties`
--
ALTER TABLE `properties`
  ADD CONSTRAINT `FK568hb1aw2a3dmwiohrfq70let` FOREIGN KEY (`idAddress`) REFERENCES `addresses` (`idAddress`);

--
-- Contraintes pour la table `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`idProperty`) REFERENCES `properties` (`idProperty`);

--
-- Contraintes pour la table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`idProperty`) REFERENCES `properties` (`idProperty`);

--
-- Contraintes pour la table `stateelement`
--
ALTER TABLE `stateelement`
  ADD CONSTRAINT `stateelement_ibfk_1` FOREIGN KEY (`idElement`) REFERENCES `elements` (`idElement`),
  ADD CONSTRAINT `stateelement_ibfk_2` FOREIGN KEY (`idInventory`) REFERENCES `inventory` (`idInventory`);

--
-- Contraintes pour la table `stateroom`
--
ALTER TABLE `stateroom`
  ADD CONSTRAINT `stateroom_ibfk_1` FOREIGN KEY (`idInventory`) REFERENCES `inventory` (`idInventory`),
  ADD CONSTRAINT `stateroom_ibfk_2` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`idRoom`);

--
-- Contraintes pour la table `wallelements`
--
ALTER TABLE `wallelements`
  ADD CONSTRAINT `FK55b00cdi5imf7v65j2ksgl18y` FOREIGN KEY (`idElement`) REFERENCES `elements` (`idElement`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
