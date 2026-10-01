# AutoLoc

Plateforme de gestion de location de véhicules multi-agences.
Projet réalisé dans le cadre de l'UP ASI (Architecture des Systèmes d'Information) à ESPRIT.

## Objectifs du projet

- Gérer le parc de véhicules de plusieurs agences de location.
- Permettre aux clients de consulter les véhicules disponibles et de réserver en ligne.
- Permettre aux agents d'agence de gérer les locations (départ, retour, facturation).
- Offrir aux responsables d'agence un suivi de l'activité de leur agence.
- Offrir à l'administrateur la gestion globale de la plateforme (agences, utilisateurs).
- Exposer une API REST documentée (Swagger UI) et testée (JUnit, Mockito, MockMvc).

## Acteurs identifiés

| Acteur | Rôle |
|---|---|
| Client | Recherche un véhicule, réserve, consulte l'historique de ses locations |
| Agent d'agence | Enregistre les départs et retours, gère les clients et les locations au comptoir |
| Responsable d'agence | Supervise son agence : véhicules, agents, indicateurs d'activité |
| Administrateur | Gère les agences, les comptes utilisateurs et la configuration globale |

## Cas d'utilisation (première liste)

**Client**
- Créer un compte et s'authentifier
- Rechercher des véhicules disponibles (agence, dates, catégorie)
- Réserver / annuler une réservation
- Consulter l'historique de ses locations

**Agent d'agence**
- Enregistrer un client
- Créer une location à partir d'une réservation ou au comptoir
- Enregistrer le départ et le retour d'un véhicule (état, kilométrage)
- Émettre la facture d'une location

**Responsable d'agence**
- Gérer les véhicules de l'agence (ajout, maintenance, indisponibilité)
- Gérer les agents de l'agence
- Consulter les statistiques (taux d'occupation, chiffre d'affaires)

**Administrateur**
- Créer et gérer les agences
- Gérer les comptes et les rôles des utilisateurs
- Superviser l'ensemble de la plateforme

## Stack technique

| Catégorie | Outils |
|---|---|
| Langage / Build | Java 17, Maven |
| Framework | Spring Boot 4, Spring Data JPA, Spring MVC |
| Base de données | MySQL / MariaDB (développement), H2 (tests) |
| Productivité | Lombok |
| Documentation API | springdoc-openapi (Swagger UI) |
| Tests | JUnit 5, Mockito, MockMvc |
| Outillage | IntelliJ IDEA Ultimate, Git/GitHub, Postman |

## Prérequis

- JDK 17 (`java -version` doit afficher 17.x.x)
- IntelliJ IDEA Ultimate (licence étudiante)
- MySQL (ou XAMPP) avec une base `autoloc_db`
- Postman
- Git

## Installation et lancement

```bash
git clone https://github.com/mariemna/AutoLoc.git
cd AutoLoc
```

1. Démarrer MySQL (par exemple via XAMPP) puis créer la base de données :
```sql
   CREATE DATABASE autoloc_db CHARACTER SET utf8mb4;
```
2. Vérifier `src/main/resources/application.properties` (utilisateur et mot de passe MySQL).
3. Lancer l'application depuis IntelliJ (classe `AutoLocApplication`) ou avec `mvn spring-boot:run`.
4. L'application écoute sur `http://localhost:8081` (`server.port=8081`).

## Avancement

- [x] Atelier 0 : environnement de développement installé et vérifié
- [ ] Atelier 1 et suivants

## Équipe

- Mariem Najahi (mariem.najahi@esprit.tn)

## Preuve de l'environnement

- Application démarrée : `docs/environnement.png`
- Collection Postman AutoLoc-API : `docs/postman.png`