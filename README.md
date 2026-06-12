# 🗂️ CollabTask — Application Collaborative de Gestion de Tâches

> Une solution complète de gestion de projets en équipe, inspirée de Trello et Jira, enrichie par l'Intelligence Artificielle.

---

## 📌 À propos du projet

**CollabTask** est une application web full-stack qui permet à des équipes de gérer leurs projets de manière collaborative et efficace. Les utilisateurs peuvent créer des workspaces, organiser leurs tâches sur des boards Kanban, assigner des membres, suivre les deadlines et bénéficier d'un assistant IA pour la description automatique des tâches.

---

## ✨ Fonctionnalités clés

- 📋 **Gestion de workspaces** — Créez des espaces de travail dédiés par projet ou équipe
- 🗃️ **Boards Kanban** — Visualisez l'avancement des tâches (Backlog → En cours → Terminé)
- ✅ **CRUD complet des tâches** — Créez, modifiez, assignez et supprimez des tâches
- 👥 **Gestion des membres** — Invitez des collaborateurs avec des permissions par rôle
- 🏷️ **Labels & Due Dates** — Catégorisez et planifiez vos tâches
- 💬 **Commentaires** — Échangez directement sur chaque tâche
- 🤖 **Assistant IA (Groq API)** — Génération automatique de descriptions de tâches via l'IA

---

## 🏗️ Architecture

```
CollabTask/
├── collab-front/          # Frontend Angular (TypeScript)
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/    # Composants UI (board, task, workspace...)
│   │   │   ├── services/      # Services Angular (HTTP, Auth, AI...)
│   │   │   └── models/        # Interfaces TypeScript
│   └── ...
│
├── collab_backend/        # Backend Spring Boot (Java)
│   ├── src/main/java/
│   │   ├── controllers/   # REST API Controllers
│   │   ├── services/      # Business Logic
│   │   ├── repositories/  # Data Access Layer (Repository Pattern)
│   │   └── models/        # Entités JPA
│   └── ...
│
└── README.md
```

### Flux de communication

```
Utilisateur → Angular (Frontend) → REST API → Spring Boot (Backend) → Base de données
                                        ↕
                                   Groq API (IA)
```

---

## 🛠️ Stack Technique

| Couche       | Technologie          |
|--------------|----------------------|
| Frontend     | Angular, TypeScript, HTML/CSS |
| Backend      | Spring Boot (Java)   |
| Base de données | PostgreSQL / H2   |
| IA           | Groq API             |
| Versioning   | Git & GitHub         |
| Qualité      | SonarQube            |

---

## 🚀 Installation & Lancement

### Prérequis
- Node.js 18+
- Java 17+
- Angular CLI (`npm install -g @angular/cli`)
- Maven

### Backend (Spring Boot)

```bash
cd collab_backend
mvn clean install
mvn spring-boot:run
```

Le backend démarre sur `http://localhost:8080`

### Frontend (Angular)

```bash
cd collab-front
npm install
ng serve
```

L'application est accessible sur `http://localhost:4200`

---

## 🤖 Intégration IA — Groq API

La fonctionnalité IA permet de générer automatiquement une description détaillée et structurée pour chaque tâche.

**Comment ça marche :**
1. L'utilisateur entre le titre de la tâche
2. Le service Angular envoie une requête au backend
3. Le backend appelle l'API Groq avec un prompt structuré
4. La description générée est renvoyée et pré-remplie dans le formulaire

---

## 🔁 Workflow Git

```
main
 └── develop
       ├── feature/task-management
       ├── feature/ai-integration
       ├── feature/workspace-members
       └── hotfix/fix-auth-token
```

- Toutes les features passent par des **Pull Requests** vers `develop`
- La branche `main` reçoit uniquement les versions stables
- Tag de release : `v1.0`

---

## 🧹 Qualité & Design Pattern

### Design Pattern appliqué : **Repository Pattern**

Sépare la logique d'accès aux données de la logique métier, permettant un code plus maintenable et testable.

### Corrections SonarQube
- Suppression des variables non utilisées
- Correction des blocs try/catch vides

---

## 👥 Équipe

| Membre | Rôle |
|--------|------|
| Motakki Hafsa | Project manager |
| Mamoudou Hamidou | Workflow & Release Manager |
| Louleb Ayoub | Feature & AI Lead |
| Ouadouss Wissal | Quality & Refactoring Lead |
| Aymen Karkouri Idrissi | Business & Documentation Lead |
| Ait Said Youssef | Kanban View |

---

## 📄 Licence

Projet académique — Université Mundiapolis 2025/2026
