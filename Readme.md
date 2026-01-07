# Workout Program REST API - LF08 Projekt

Eine Spring Boot REST API zur Verwaltung von Fitnessstudio-Trainingsprogrammen mit JWT-Authentifizierung über Authentik.

## Projektübersicht

Dieses Projekt ist eine vollständige REST API, die mit Spring Boot erstellt wurde und es Benutzern ermöglicht, Trainingsprogramme zu erstellen, zu lesen, zu aktualisieren und zu löschen. Jedes Trainingsprogramm enthält:
- Programmname
- Zielmuskelgruppe
- Schwierigkeitsgrad (Anfänger, Fortgeschritten, Experte)
- Dauer in Wochen
- Einheiten pro Woche

## Verwendete Technologien

- **Spring Boot 3.3.3** - Anwendungs-Framework
- **Spring Data JPA** - Datenzugriffsschicht
- **PostgreSQL** - Datenbank
- **Spring Security** - JWT-Authentifizierung mit Authentik
- **Hibernate** - ORM
- **Lombok** - Reduzierung von Boilerplate-Code
- **Jakarta Validation** - Eingabevalidierung
- **Gradle** - Build-Tool
- **Docker** - PostgreSQL-Containerisierung

## Architektur

Das Projekt folgt einer Schichtenarchitektur:

1. **Entity-Schicht** - `WorkoutProgramEntity` (Datenbankmodell)
2. **DTO-Schicht** - `WorkoutProgramCreateDto` und `WorkoutProgramGetDto` (Datenübertragung)
3. **Repository-Schicht** - `WorkoutProgramRepository` (Datenbankzugriff)
4. **Mapper-Schicht** - `WorkoutProgramMapper` (DTO/Entity-Konvertierung)
5. **Service-Schicht** - `WorkoutProgramService` (Geschäftslogik)
6. **Controller-Schicht** - `WorkoutProgramController` (REST-Endpunkte)

## Voraussetzungen

* Java 17 oder höher
* Docker https://docs.docker.com/get-docker/
* Docker Compose (bei Windows und Mac in Docker enthalten) https://docs.docker.com/compose/install/

## Einrichtung und Installation

### 1. Repository klonen

```bash
git clone https://github.com/wakeyspoon/lf8-gym-workout-project.git
cd lf8-gym-workout-project
```

### 2. PostgreSQL-Datenbank starten

```bash
docker compose up -d
```

Dies startet einen PostgreSQL-Container auf Port 5432.

### 3. Anwendung ausführen

```bash
./gradlew bootRun
```

Die Anwendung startet auf `http://localhost:8080`

## API-Endpunkte

### Authentifizierung erforderlich

Alle workout-programs-Endpunkte erfordern ein gültiges JWT-Bearer-Token von Authentik.

### Verfügbare Endpunkte

| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| GET | `/workout-programs` | Alle Trainingsprogramme abrufen |
| GET | `/workout-programs/{id}` | Ein bestimmtes Trainingsprogramm nach ID abrufen |
| POST | `/workout-programs` | Ein neues Trainingsprogramm erstellen |
| PUT | `/workout-programs/{id}` | Ein bestehendes Trainingsprogramm aktualisieren |
| DELETE | `/workout-programs/{id}` | Ein Trainingsprogramm löschen |
| GET | `/workout-programs/findByMuscleGroup?targetMuscleGroup={gruppe}` | Programme nach Muskelgruppe suchen |
| GET | `/workout-programs/findByDifficulty?difficultyLevel={stufe}` | Programme nach Schwierigkeitsgrad suchen |

## Beispieldaten

Die Anwendung erstellt beim Start automatisch 3 Beispiel-Trainingsprogramme:

1. **Starting Strength** - Anfänger, Ganzkörper, 12 Wochen, 3 Einheiten/Woche
2. **PPL Hypertrophy** - Fortgeschritten, Push/Pull/Legs, 16 Wochen, 6 Einheiten/Woche
3. **German Volume Training** - Experte, Hohes Volumen, 6 Wochen, 4 Einheiten/Woche

## API testen

### 1. JWT-Token von Authentik holen

1. [GetBearerToken.http](GetBearerToken.http) auf Projektebene öffnen
2. Neben der Request auf den grünen Pfeil klicken (oder "Send Request")
3. Das `access_token` aus der Response kopieren

### 2. Endpunkte testen

Verwenden Sie die Datei `WorkoutProgramRequests.http`, um alle Endpunkte zu testen. Ersetzen Sie `YOUR_TOKEN_HERE` durch Ihr tatsächliches JWT-Token.

### Beispiel Request Body (POST)

```json
{
    "programName": "5x5 Kraftprogramm",
    "targetMuscleGroup": "Ganzkörper",
    "difficultyLevel": "Fortgeschritten",
    "durationWeeks": 12,
    "sessionsPerWeek": 3
}
```

### Beispiel Response (GET)

```json
{
    "id": 1,
    "programName": "Starting Strength",
    "targetMuscleGroup": "Full Body",
    "difficultyLevel": "Beginner",
    "durationWeeks": 12,
    "sessionsPerWeek": 3
}
```

## Validierung

Die API enthält Eingabevalidierung:
- `programName`: Erforderlich, 3-100 Zeichen
- `targetMuscleGroup`: Erforderlich, 3-50 Zeichen
- `difficultyLevel`: Erforderlich, 3-30 Zeichen
- `durationWeeks`: Erforderlich, mindestens 1
- `sessionsPerWeek`: Erforderlich, mindestens 1

## Projektstruktur

```
src/main/java/de/szut/lf8_starter/
├── workout/
│   ├── WorkoutProgramEntity.java
│   ├── WorkoutProgramRepository.java
│   ├── WorkoutProgramMapper.java
│   ├── WorkoutProgramService.java
│   ├── WorkoutProgramController.java
│   └── dto/
│       ├── WorkoutProgramCreateDto.java
│       └── WorkoutProgramGetDto.java
├── security/
│   └── AuthentikSecurityConfig.java
└── config/
    └── SampleDataCreator.java
```

## Swagger-Dokumentation
```
http://localhost:8080/swagger
```

## Docker-Verwaltung

### PostgreSQL stoppen
```bash
docker compose down
```

### Datenbank zurücksetzen (bei Problemen)
```bash
docker compose down
docker volume rm lf8starter_postgres_data
docker compose up -d
```

## Sicherheit

- Alle `/workout-programs/**` Endpunkte erfordern Authentifizierung
- JWT-Token werden über den Authentik OAuth2-Provider validiert
- Token verfallen nach 2 Stunden

## Entwicklungshinweise

- Verwendet Hibernate für automatische Datenbank-Schema-Erstellung
- Datenbanktabellen werden beim Start erstellt
- Das Projekt verwendet Java 17 (konfiguriert in `gradle.properties`)
- `.http`-Dateien enthalten sensible Token und sind von der Versionskontrolle ausgeschlossen

## Git Commit-Historie

Das Projekt wurde schrittweise mit organisierten Commits erstellt:
1. WorkoutProgramEntity mit Feldern
2. DTOs (CreateDto mit Validierung und GetDto)
3. Repository mit benutzerdefinierten Query-Methoden
4. Mapper für DTO-Entity-Konvertierung
5. Service mit CRUD-Operationen und benutzerdefinierten Abfragen
6. Controller mit vollständigen REST-API-Endpunkten
7. Aktualisierung der Sicherheitskonfiguration
8. Beispiel-Trainingsprogrammdaten
9. Gradle-Konfiguration für Java 17

## Autor

Erstellt als Teil des LF08 Berufsschulmoduls.
