# Marche à suivre

## Analyse de l'US et de ses critères d'acceptation

US-01 – Inscrire un **LECTEUR**
- En tant que bibliothécaire, je veux **INSCRIRE** un nouveau **LECTEUR** avec son **NOM** et son **EMAIL** afin qu’il puisse emprunter des ouvrages.

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


## Résultats:

Notre cas d'usage est le suivant : Inscription d'un lecteur à l'aide de nom et email.

## Etapes à suivre :

1. J'écris mon test unitaire concernant le premier scénario ou critère d'acceptation
2. Je fais passer au vert mon test
3. Je refactor si besoin
4. Je passe au second critère et ainsi de suite.