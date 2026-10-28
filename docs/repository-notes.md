# Atelier 3 : couche Repository Spring Data JPA

## Repositories créés

Le projet expose un repository par entité persistante :

| Repository | Entité | Interface de base |
|---|---|---|
| `IAgenceRepository` | `Agence` | `JpaRepository<Agence, Long>` |
| `IEmployeRepository` | `Employe` | `JpaRepository<Employe, Long>` |
| `IVehiculeRepository` | `Vehicule` | `JpaRepository<Vehicule, Long>` |
| `IEquipementRepository` | `Equipement` | `JpaRepository<Equipement, Long>` |
| `IClientRepository` | `Client` | `JpaRepository<Client, Long>` |
| `IReservationRepository` | `Reservation` | `JpaRepository<Reservation, Long>` |
| `IContratRepository` | `Contrat` | `JpaRepository<Contrat, Long>` |
| `IPaiementRepository` | `Paiement` | `JpaRepository<Paiement, Long>` |
| `IMaintenanceRepository` | `Maintenance` | `JpaRepository<Maintenance, Long>` |

`JpaRepository` a été retenu car il regroupe les opérations CRUD, le tri, la
pagination, `flush` et les opérations spécifiques JPA. Le type `Long`
correspond aux identifiants générés par les entités AutoLoc.

Les paiements disposent de leur repository pour permettre leur consultation
directe. En revanche, leur création et leur suppression métier doivent rester
pilotées par `Contrat`, car la relation `Contrat -> Paiement` utilise
`CascadeType.ALL` et `orphanRemoval`.

## Vérifications effectuées

- Les neuf interfaces sont dans le package `tn.esprit.mariemnajahi.repository`.
- Chaque interface est détectable par Spring Data JPA.
- Aucun repository ne contient d'implémentation manuelle.
- Les noms d'entités et les types d'identifiants correspondent au domaine.
- Les entités n'utilisent pas `@Data`, afin d'éviter les récursions dans les
  associations bidirectionnelles.

## Revue qualité

Une revue manuelle des fichiers a permis de vérifier les points suivants :

| Point contrôlé | Risque | Correction / résultat |
|---|---|---|
| Imports des repositories | Imports inutilisés ou mauvais package | Chaque import pointe vers l'entité réellement utilisée |
| Nom des interfaces | Détection Spring Data non conforme | Convention `I<Entite>Repository` respectée |
| Implémentation manuelle | Code inutile et fragile | Les neuf interfaces délèguent leur implémentation à Spring Data |
| Entités associées avec `@Data` | Récursion `toString`/`hashCode` | Les entités utilisent `@Getter` et `@Setter` |

## Démonstration CRUD

La classe `Atelier3CrudDemo` est disponible avec le profil Spring `atelier3`.
Elle n'est pas exécutée par défaut afin de ne pas insérer ni supprimer de
données lors d'un démarrage normal de l'application.

Pour l'exécuter :

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=atelier3
```

La console affiche successivement `save`, `findById` et `deleteById`. Le
client de démonstration est créé avec une adresse e-mail et un permis uniques,
puis supprimé à la fin de l'exécution.

## Captures à intégrer au compte rendu

1. Les neuf interfaces du package `repository`, avec une interface étendant
   `JpaRepository<Entite, Long>`.
2. `docs/repository-notes.md` montrant le tableau des repositories et le choix
   de `JpaRepository`.
3. La console du démarrage normal avec
   `Found 9 JPA repository interfaces`.
4. La console du profil `atelier3` montrant `save`, `findById` puis
   `deleteById`.
5. Les logs SQL associés aux opérations CRUD et à la connexion MySQL.
6. Le panneau SonarQube for IDE après l'analyse, puis les fichiers corrigés.
7. IntelliJ ou Git montrant les fichiers suivis et le commit de l'Atelier 3.
