#  Gestion des Comptes Bancaires en Java

##  Description du projet
Ce projet est une application console développée en **Java 8** qui permet à une banque d’automatiser la gestion des comptes bancaires et de leurs opérations courantes.  
L’application permet de créer des comptes, d’effectuer des dépôts, retraits et virements, ainsi que de consulter les soldes et l’historique des opérations.

L’objectif est de mettre en place une architecture claire et modulaire avec différentes couches (présentation, métier, utilitaire) et d’intégrer des règles métiers spécifiques aux **comptes courants** et **comptes épargnes**.

---

##  Technologies utilisées
- **Java 8** (JDK 1.8)
- **Java Time API** (`java.time.LocalDateTime`) pour la gestion des dates
- **Collections Framework** (`HashMap`, `ArrayList`) pour le stockage en mémoire
- **UUID** pour générer des identifiants uniques d’opérations
- **Paradigme POO** : héritage, abstraction, polymorphisme
- **Gestion des exceptions** avec `try-catch`

---

##  Structure du projet
src/
│── Controller/
│ └── BanqueController.java # Couche de présentation (UI/Menu)
│
│── Model/
│ ├── Compte.java # Classe abstraite
│ ├── CompteCourant.java # Hérite de Compte
│ ├── CompteEpargne.java # Hérite de Compte
│ ├── Operation.java # Classe abstraite
│ ├── Versement.java # Hérite de Operation
│ └── Retrait.java # Hérite de Operation
│
│── Main.java # Point d’entrée du programme



###  Détails des classes principales
- **Compte (abstraite)** : code, solde, liste des opérations
- **CompteCourant** : possède un découvert autorisé, pas d’intérêts
- **CompteEpargne** : possède un taux d’intérêt, retrait limité au solde disponible
- **Operation (abstraite)** : numéro (UUID), date, montant
- **Versement** : source (ex: virement externe, dépôt espèces)
- **Retrait** : destination (ex: ATM, chèque, virement sortant)

---

##  Prérequis
- Installer **Java 8 (JDK 1.8)**
- Un IDE (IntelliJ IDEA, Eclipse, VS Code) ou exécution en console
- Compiler le projet :
  ```bash
  javac src/Main.java
Lancer l’application :

bash
Copier le code
java Main


Exemple du menu principal :

====== Menu Banque ======
1. Créer un compte
2. Effectuer un versement
3. Effectuer un retrait
4. Effectuer un virement
5. Consulter le solde
6. Consulter l’historique des opérations
0. Quitter
   =========================
    Fonctionnalités principales
    Créer un compte (courant ou épargne)

 Effectuer un versement

 Effectuer un retrait

 Effectuer un virement (entre comptes existants)

 Consulter le solde d’un compte

 Consulter l’historique des opérations

 Auteurs
Projet développé par Asma Lachhab

Formation : YouCode
