# Atelier 2 : associations, cascade et fetch

Les associations utilisent `FetchType.LAZY` afin qu'une lecture d'une entité
ne charge pas automatiquement toute sa graphe d'objets liés. Les associations
`@ManyToOne` et `@OneToOne`, qui sont `EAGER` par défaut en JPA, sont donc
explicitement configurées en `LAZY`.

| Association | Mapping | Fetch | Cascade |
|---|---|---|---|
| `Agence -> Vehicule` | `Agence.vehicules` inverse de `Vehicule.agence` | `LAZY` | Aucune : un véhicule peut survivre à son agence |
| `Agence -> Employe` | `Agence.employes` inverse de `Employe.agence` | `LAZY` | Aucune |
| `Vehicule <-> Equipement` | `Vehicule.equipements` propriétaire, table `vehicule_equipement` | `LAZY` | Aucune : un équipement est partagé |
| `Client -> Reservation` | `Client.reservations` inverse de `Reservation.client` | `LAZY` | `PERSIST` pour enregistrer les nouvelles réservations avec leur client |
| `Reservation -> Vehicule` | `Reservation.vehicule` propriétaire | `LAZY` | Aucune |
| `Reservation <-> Contrat` | `Contrat.reservation` propriétaire avec FK `id_reservation` | `LAZY` | `ALL` sur le côté `Reservation` |
| `Vehicule -> Maintenance` | `Vehicule.maintenances` inverse de `Maintenance.vehicule` | `LAZY` | `PERSIST` |
| `Contrat -> Paiement` | `Contrat.paiements` inverse de `Paiement.contrat` | `LAZY` | `ALL` et `orphanRemoval = true` |

`mappedBy` est utilisé sur chaque côté inverse afin d'éviter une clé
étrangère ou une table de jointure supplémentaire. Pour
`Vehicule <-> Equipement`, un `Set` est utilisé car la relation représente un
ensemble d'équipements partagés et non une collection ordonnée.

Les cascades restent limitées aux objets dépendants du parent. Les paiements
ont donc `ALL` et `orphanRemoval`, tandis que les véhicules, employés et
équipements ne sont jamais supprimés automatiquement avec une agence ou un
autre véhicule.
