# Ma todo-list

## Première User Story

Voici une première User Story simple et “verticale” pour démarrer en architecture hexagonale sur une gestion de bibliothèque.
Titre: US-01 – Inscrire un lecteur
Description
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

Définition de prêt (DoD) orientée “hexagone”
- Cas d’usage (application) RegisterReader implémenté derrière un port (inbound).
- Port secondaire ReaderRepository défini avec findByEmail(email) et save(reader).
- Adaptateur entrant REST: POST /readers acceptant name et email, renvoyant 201 en cas de succès.
- Adaptateur sortant persistance (ex. JPA) respectant le port.
- Tests:
    - Unitaires sur le cas d’usage (succès, doublon email, validation).
    - Intégration sur l’adaptateur REST (201/409/400).

- Journalisation minimale des erreurs et validations.

Hors scope pour cette US
- Authentification/autorisation.
- Mise à jour/suppression du lecteur.
- Emprunts/réservations de livres.
- Notifications email.

Exemples de tests
- Succès: name="Alice Martin", email="alice@example.org" -> 201, status=ACTIVE.
- Doublon: deux inscriptions avec "bob@example.org" -> la seconde renvoie 409.
- Validation: name=" A " (trim -> trop court) -> 400; email="not-an-email" -> 400.

Si tu veux, je peux te proposer le découpage technique (ports/adaptateurs, DTO de commande, modèle de domaine et schéma minimal) ou la US suivante logique (consulter un lecteur ou cataloguer un livre).


