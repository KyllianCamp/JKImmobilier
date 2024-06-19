# Projet Michka

## Description

Michka est une application pour une agence immobilière qui souhaite digitaliser son travail afin de simplifier ses tâches quotidiennes. Cette application permet de gérer les biens immobiliers, les locations, les utilisateurs et bien plus encore de manière efficace et centralisée.

## Prérequis

- Java 11 ou supérieur
- Maven
- MySQL
- JavaFX 11 ou supérieur

## Installation

1. **Importer la base de données** :
   - Téléchargez le script SQL pour la base de données depuis le dossier `database`.
   - Importez ce script dans votre serveur MySQL pour créer les tables nécessaires.

2. **Télécharger JavaFX** :
   - Téléchargez le .jar de JavaFX depuis [OpenJFX](https://openjfx.io/).
   - Ajoutez JavaFX à votre projet en modifiant le `includePath` pour pointer vers la librairie téléchargée.

3. **Configurer la base de données et les variables d'environnement** :
   - Copiez le fichier `persistence.sample.xml` et renommez-le en `persistence.xml`.
   - Remplissez le fichier `persistence.xml` avec les informations de connexion à votre base de données MySQL.
   - Copiez le fichier `sample.env` et renommez-le en `.env`.
   - Remplissez le fichier `.env` avec les variables d'environnement nécessaires (comme les informations de connexion à la base de données).

## Utilisation

1. Clonez le dépôt du projet :
   ```sh
   git clone <url-du-depot>
   cd <nom-du-dossier>

2. Compilez et exécutez le projet 

3. Assurez-vous que les fichiers persistence.xml et .env sont correctement configurés avant de lancer l'application.