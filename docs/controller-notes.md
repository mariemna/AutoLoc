# Atelier 5 : couche Controller REST et documentation Swagger

## Contrôleurs et endpoints

Les contrôleurs REST se trouvent dans
`tn.esprit.mariemnajahi.web.controller`. Ils utilisent l'injection par
constructeur générée par Lombok (`@RequiredArgsConstructor`) et délèguent les
opérations aux interfaces de service.

| Entité | Base URL | GET collection | GET détail | POST | PUT | DELETE |
|---|---|---|---|---|---|---|
| Agence | `/api/agences` | `/api/agences` | `/api/agences/{id}` | `/api/agences` | `/api/agences/{id}` | `/api/agences/{id}` |
| Employe | `/api/employes` | `/api/employes` | `/api/employes/{id}` | `/api/employes` | `/api/employes/{id}` | `/api/employes/{id}` |
| Vehicule | `/api/vehicules` | `/api/vehicules` | `/api/vehicules/{id}` | `/api/vehicules` | `/api/vehicules/{id}` | `/api/vehicules/{id}` |
| Equipement | `/api/equipements` | `/api/equipements` | `/api/equipements/{id}` | `/api/equipements` | `/api/equipements/{id}` | `/api/equipements/{id}` |
| Client | `/api/clients` | `/api/clients` | `/api/clients/{id}` | `/api/clients` | `/api/clients/{id}` | `/api/clients/{id}` |
| Reservation | `/api/reservations` | `/api/reservations` | `/api/reservations/{id}` | `/api/reservations` | `/api/reservations/{id}` | `/api/reservations/{id}` |
| Contrat | `/api/contrats` | `/api/contrats` | `/api/contrats/{id}` | `/api/contrats` | `/api/contrats/{id}` | `/api/contrats/{id}` |
| Paiement | `/api/paiements` | `/api/paiements` | `/api/paiements/{id}` | `/api/paiements` | `/api/paiements/{id}` | `/api/paiements/{id}` |
| Maintenance | `/api/maintenances` | `/api/maintenances` | `/api/maintenances/{id}` | `/api/maintenances` | `/api/maintenances/{id}` | `/api/maintenances/{id}` |

Les créations répondent avec `201 Created`, les consultations et modifications
avec `200 OK`, et les suppressions avec `204 No Content`. Pour une mise à
jour, l'identifiant du chemin est prioritaire sur celui éventuellement présent
dans le JSON.

## Swagger / OpenAPI

L'interface Swagger UI est disponible après le démarrage de l'application à
l'adresse :

```text
http://localhost:8080/swagger-ui.html
```

Le document OpenAPI brut est disponible ici :

```text
http://localhost:8080/v3/api-docs
```

Chaque contrôleur est décrit par `@Tag` et chacune de ses opérations par
`@Operation`.

## Captures à fournir

1. Démarrer l'application avec `.\mvnw.cmd spring-boot:run` (ou la
   configuration de base de données habituelle).
2. Ouvrir `http://localhost:8080/swagger-ui.html`.
3. Développer les neuf groupes et capturer au minimum une vue montrant la
   documentation des opérations CRUD.
4. Exécuter un `GET` de collection, un `GET` par identifiant, un `POST`, un
   `PUT` et un `DELETE` depuis Swagger UI pour une entité de démonstration.
5. Capturer les réponses HTTP et les codes `200`, `201` et `204`.

Les associations JPA restent accessibles dans les objets retournés, tandis
que les côtés inverses doivent être ignorés ou limités dans une représentation
JSON si une relation bidirectionnelle est chargée, afin d'éviter une
référence circulaire.
