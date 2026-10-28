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
| `IAgenceService` | `AgenceServiceImpl` | `Agence` | CRUD |
| `IClientService` | `ClientServiceImpl` | `Client` | CRUD |
| `IContratService` | `ContratServiceImpl` | `Contrat` | CRUD |
| `IEmployeService` | `EmployeServiceImpl` | `Employe` | CRUD |
| `IEquipementService` | `EquipementServiceImpl` | `Equipement` | CRUD |
| `IMaintenanceService` | `MaintenanceServiceImpl` | `Maintenance` | CRUD |
| `IReservationService` | `ReservationServiceImpl` | `Reservation` | CRUD |
| `IVehiculeService` | `VehiculeServiceImpl` | `Vehicule` | CRUD |
| `IPaiementService` | `PaiementServiceImpl` | `Paiement` | `findById`, `findAll` uniquement |

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

## F. Comparaison des injections

| Mode | Champ `final` | Dépendance visible | Utilisable hors conteneur | Qualité |
|---|---|---|---|---|
| Constructeur | Oui | Oui, dans le constructeur | Oui, si le repository est fourni | Recommandé |
| Attribut `@Autowired` | Non | Non, dépendance cachée | Non, champ nul hors Spring | Signalé par SonarQube for IDE, règle S6813 |

## G. Diagnostic des erreurs du conteneur

- **Message 1 — `NoSuchBeanDefinitionException`** : `IContratService` n'est
  pas détecté, généralement parce que `@Service` manque sur l'implémentation
  ou que la classe est hors du package scanné. Ajouter `@Service` et vérifier
  le package racine.
- **Message 2 — `NoUniqueBeanDefinitionException`** : deux beans répondent au
  même type. On peut annoter une classe avec `@Primary`, ou utiliser
  `@Qualifier` sur le paramètre du constructeur pour choisir explicitement le
  bean.
- **Message 3 — `BeanCurrentlyInCreationException`** : deux services
  dépendent directement l'un de l'autre. Il faut supprimer la dépendance
  circulaire, par exemple en déplaçant la responsabilité commune dans un
  troisième service.

## H. Questions de compréhension

1. Il n'y a pas de `new ContratServiceImpl` écrit dans le code applicatif :
   `SpringApplication.run` démarre le conteneur, qui détecte `@Service`,
   instancie la classe et injecte le repository.
2. Un contrôleur dépend de `IContratService` pour rester découplé de
   l'implémentation et pouvoir la remplacer ou la tester plus facilement.
3. Un singleton est partagé par plusieurs requêtes et doit donc rester sans
   état mutable. Un champ contenant le contrat courant pourrait être écrasé
   par une autre requête et produire une réponse incohérente.
4. `update` recharge l'entité existante afin de préserver son identité, ses
   associations et ses collections. Copier directement l'objet reçu peut
   écraser des colonnes ou vider une relation `orphanRemoval`.
5. `@Component` permet la détection automatique d'une classe ; `@Bean`
   déclare explicitement l'objet renvoyé par une méthode de configuration.
   `@Primary` désigne le bean choisi par défaut, tandis que `@Qualifier`
   impose un choix précis. `IPaiementService` est en lecture seule car les
   paiements sont créés et supprimés par `Contrat` via la cascade et
   `orphanRemoval`.

## I. Anomalies SonarQube for IDE corrigées

| Règle | Anomalie | Correction |
|---|---|---|
| S6813 | Injection par attribut avec `@Autowired` | Injection par constructeur avec `@RequiredArgsConstructor` et champ `final` |
| S112 | Lancer une exception générique | Utilisation de `ResourceNotFoundException` pour une ressource absente |
| S1186 | Méthode vide ou code inutile | Suppression du code mort et conservation de services centrés sur leur responsabilité |
