# Atelier 4 : couche Service, IoC et injection de dépendances

## A. Responsabilité de la couche Service

La couche Service porte les règles métier entre les entités JPA et les
repositories Spring Data. Elle ne remplace pas le repository : elle contrôle
les entrées, centralise les recherches avec gestion d'erreur et orchestre les
opérations de création, consultation, modification et suppression.

Chaque contrat de service est une interface dans
`tn.esprit.mariemnajahi.service`, et chaque interface possède une
implémentation annotée `@Service`.

## B. IoC et injection par constructeur

Spring détecte les neuf implémentations grâce à `@Service`. Les dépendances
sont des champs `final` de type interface repository et Lombok
`@RequiredArgsConstructor` génère leur constructeur. Le conteneur Spring
injecte donc les repositories par constructeur (sans champ `@Autowired`), ce
qui rend les dépendances explicites et facilite les tests unitaires.

## C. Services et opérations exposées

| Interface | Implémentation | Entité | Opérations |
|---|---|---|---|
| `IAgenceService` | `AgenceService` | `Agence` | CRUD |
| `IClientService` | `ClientService` | `Client` | CRUD |
| `IContratService` | `ContratService` | `Contrat` | CRUD |
| `IEmployeService` | `EmployeService` | `Employe` | CRUD |
| `IEquipementService` | `EquipementService` | `Equipement` | CRUD |
| `IMaintenanceService` | `MaintenanceService` | `Maintenance` | CRUD |
| `IReservationService` | `ReservationService` | `Reservation` | CRUD |
| `IVehiculeService` | `VehiculeService` | `Vehicule` | CRUD |
| `IPaiementService` | `PaiementService` | `Paiement` | `findById`, `findAll` uniquement |

Les opérations communes sont `save`, `findById`, `findAll`, `update` et
`deleteById`. Des alias `add*`, `retrieve*` et `delete*` sont fournis sur les
contrats pour conserver le vocabulaire usuel des ateliers précédents.
Les paiements restent en lecture seule au niveau Service ; leur création et
leur suppression sont pilotées par `Contrat`, conformément à la cascade et à
`orphanRemoval` du domaine.

## D. Règles métier et cohérence des mises à jour

- Une création avec un identifiant non nul est refusée.
- `findById` lève `ResourceNotFoundException` si l'identifiant est absent ou
  inconnu.
- Une suppression vérifie d'abord l'existence de l'entité.
- `Contrat.montantTotal` et `Vehicule.tarifJournalier` ne peuvent pas être
  négatifs.
- Les dates de fin de `Reservation` et `Maintenance` ne peuvent pas précéder
  leurs dates de début. `Maintenance` ne possède pas de champ de coût dans
  l'entité actuelle : aucune règle de coût ne peut donc être appliquée.
- Les champs obligatoires déclarés par les entités sont contrôlés, notamment
  l'identification du `Client`, le nom de l'`Agence`, les champs requis de
  l'`Employe` et le libellé de l'`Equipement`. Il n'existe pas de champ
  `quantity` sur `Equipement` dans le modèle actuel.

Une mise à jour recharge d'abord l'instance persistée, puis copie seulement
les champs scalaires modifiables. L'identifiant et les collections
(`reservations`, `paiements`, `vehicules`, `employes`, `equipements` et
`maintenances`) ne sont jamais remplacés par l'objet entrant.

## E. Vérifications

La compilation et les tests Maven vérifient l'intégration des composants
Spring, de Lombok et des nouvelles implémentations. Une revue des entités
sert de référence pour les accesseurs utilisés : aucune validation ne fait
référence à un champ absent comme `quantity` ou un coût de maintenance.
