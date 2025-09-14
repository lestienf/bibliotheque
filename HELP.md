# Ma bibliothèque

## Première User Story

Titre: US-01 – Inscrire un lecteur
- En tant que bibliothécaire, je veux inscrire un nouveau lecteur avec son nom et son email afin qu’il puisse emprunter des ouvrages.

Règles métier
- L’email du lecteur doit être unique.
- Le nom est obligatoire (trim, taille minimale configurable, p.ex. ≥ 2).
- L’email doit avoir un format valide.
- L’identifiant du lecteur est généré par le système.
- Le statut initial du lecteur est ACTIVE.

Critères d’acceptation (Given/When/Then)
- CA1 – Création réussie
    - Étant donné un nom valide et un email valide non utilisé
    - Quand je soumets la demande d’inscription
    - Alors le lecteur est créé avec un identifiant généré et le statut ACTIVE
    - Et la réponse contient id, name, email, status

- CA2 – Email déjà utilisé
    - Étant donné qu’un lecteur existe déjà avec cet email
    - Quand je soumets une nouvelle inscription avec le même email
    - Alors la création est refusée avec une erreur de duplication (409 Conflict)

- CA3 – Données invalides
    - Étant donné un nom vide ou un email invalide
    - Quand je soumets l’inscription
    - Alors la création est refusée avec une erreur de validation (400 Bad Request) et des détails sur les champs en erreur

## Deuxième User Story

US-02 – Réserver un livre
- En tant que lecteur, je veux réserver un livre afin de pouvoir l’emprunter dès qu’un exemplaire est disponible.

Règles métier
- Le lecteur doit exister et être en statut ACTIVE.
- Le livre doit exister.
- Un lecteur ne peut pas avoir plus d’une réservation active sur le même livre.
- Un lecteur ne peut pas réserver un livre qu’il a déjà en cours d’emprunt.
- Un plafond de réservations actives par lecteur est appliqué (configurable, p.ex. 5).
- Gestion des disponibilités:
  - S’il n’y a aucun exemplaire disponible, la réservation est créée en file d’attente avec un rang (status = WAITING).
  - S’il existe au moins un exemplaire disponible, un exemplaire est mis de côté (hold) pour le lecteur et la réservation passe en READY_FOR_PICKUP avec une date limite de retrait (p.ex. 48h, configurable). Passé ce délai, la réservation EXPPIRE et l’exemplaire redevient disponible.

- L’ordre de la file d’attente est FIFO par date de réservation.
- Une réservation se termine en FULFILLED lorsque l’emprunt est créé à partir de la réservation.

Critères d’acceptation (Given/When/Then)
- CA1 – Réservation en attente (pas d’exemplaire disponible)
  - Étant donné un lecteur ACTIVE et un livre sans exemplaire disponible
  - Quand je demande une réservation
  - Alors la réservation est créée avec status = WAITING une réponse 201 Created contenant id, readerId, bookId, status, position, createdAt

- CA2 – Réservation avec exemplaire disponible (mise de côté)
  - Étant donné un lecteur ACTIVE et un livre avec au moins un exemplaire disponible
  - Quand je demande une réservation
  - Alors un exemplaire est mis en attente pour le lecteur, la réservation est créée avec status = READY_FOR_PICKUP, readyUntilAt est renseigné, et la réponse est 201 Created

- CA3 – Réservation dupliquée
  - Étant donné un lecteur qui a déjà une réservation active pour ce livre
  - Quand je demande une réservation pour le même livre
  - Alors la création est refusée avec 409 Conflict

- CA4 – Règles bloquantes
  - Étant donné un lecteur qui a atteint le plafond de réservations actives OU qui a déjà un emprunt en cours sur ce livre
  - Quand je demande une réservation
  - Alors la création est refusée avec 409 Conflict

- CA5 – Entités introuvables
  - Étant donné un readerId ou bookId inexistant
  - Quand je demande une réservation
  - Alors la réponse est 404 Not Found

- CA6 – Données invalides
  - Étant donné un payload incomplet ou invalide
  - Quand je demande une réservation
  - Alors la réponse est 400 Bad Request avec les détails de validation
