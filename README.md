# Todo.ly API Testing

Proyecto de automatización de pruebas API para [Todo.ly](https://todo.ly/), desarrollado con Java, Gradle, Cucumber y REST Assured. El objetivo es validar el comportamiento de la API REST de usuarios, autenticación y proyectos mediante pruebas BDD ejecutables y reportes automáticos.

## Descripción general

Este repositorio automatiza escenarios de negocio sobre la API de Todo.ly para comprobar:

- creación y validación de usuarios
- generación y uso de tokens de autenticación
- obtención, actualización y eliminación de usuarios
- creación, consulta, modificación y borrado de proyectos
- validación de errores para casos negativos

La estrategia de pruebas se basa en Gherkin + Cucumber para mantener la lógica de negocio legible y en clientes HTTP con REST Assured para interactuar con los endpoints.

## Stack tecnológico

- Java 17
- Gradle
- Cucumber 7
- JUnit 4 / JUnit Platform
- REST Assured
- Jackson Databind
- AssertJ
- Gradle reports (HTML, JSON y XML)

## Requisitos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- JDK 17 o superior
- Gradle Wrapper incluido en el repositorio o Gradle instalado localmente
- Conexión a internet para consumir la API pública de Todo.ly

## Estructura del proyecto

```text
ProyectoApi/
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
├── README.md
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── todoly/
│   │   │           ├── client/
│   │   │           │   ├── AuthClient.java
│   │   │           │   ├── BaseClient.java
│   │   │           │   ├── ProjectClient.java
│   │   │           │   └── UserClient.java
│   │   │           ├── model/
│   │   │           │   ├── ProjectRequest.java
│   │   │           │   ├── ProjectResponse.java
│   │   │           │   ├── TokenResponse.java
│   │   │           │   ├── UserRequest.java
│   │   │           │   └── UserResponse.java
│   │   │           ├── runner/
│   │   │           │   └── CucumberTestRunner.java
│   │   │           └── stepdefinitions/
│   │   │               ├── AuthSteps.java
│   │   │               ├── CommonSteps.java
│   │   │               ├── Hooks.java
│   │   │               ├── ProjectSteps.java
│   │   │               └── UserSteps.java
│   │   └── resources/
│   │       ├── config.properties
│   │       └── features/
│   │           ├── auth.feature
│   │           ├── delete.feature
│   │           ├── get.feature
│   │           ├── post.feature
│   │           ├── project_lifecycle_token.feature
│   │           └── put.feature
│   └── main/
│       └── (vacío por el momento)
├── build/
│   └── reports/
│       ├── cucumber/
│       └── tests/
└── gradle/
    └── wrapper/
```

## Configuración

La configuración base de la API está en:

- `src/test/resources/config.properties`

Valores actuales:

```properties
base.url=https://todo.ly/api/
user.endpoint=user.json
user.current.endpoint=user/0.json
token.endpoint=authentication/token.json
projects.endpoint=projects.json
project.item.endpoint=projects/{0}.json
```

Esto permite centralizar la URL base y los endpoints sin duplicar rutas dentro del código de las pruebas.

## Features implementadas

El proyecto incluye escenarios BDD organizados por operaciones CRUD y autenticación:

- `auth.feature`
  - generación de token con credenciales válidas
  - errores por usuario inexistente, contraseña incorrecta y falta de credenciales

- `post.feature`
  - creación de usuario
  - creación de proyecto con token
  - validación de duplicados y tokens inválidos

- `get.feature`
  - consulta de perfil de usuario
  - consulta de proyectos por id
  - validación de acceso no autorizado y proyecto inexistente

- `put.feature`
  - actualización de datos de usuario
  - actualización de nombre de proyecto
  - validación de token inválido o faltante

- `delete.feature`
  - eliminación de usuario y proyectos
  - validación de id inválido y autenticación incorrecta

- `project_lifecycle_token.feature`
  - flujo end-to-end con registro de usuario, generación de token, creación, actualización y eliminación de proyecto

## Cómo ejecutar las pruebas

### Ejecutar todas las pruebas

En Windows:

```powershell
./gradlew.bat test
```

En Linux/macOS:

```bash
./gradlew test
```

### Ejecutar solo una suite o feature específica

La ejecución se gestiona a través del runner principal:

```powershell
./gradlew.bat test --tests com.todoly.runner.CucumberTestRunner
```

### Ejecutar por tags de Cucumber

```powershell
./gradlew.bat test -Dcucumber.filter.tags="@smoke"
./gradlew.bat test -Dcucumber.filter.tags="@positive"
./gradlew.bat test -Dcucumber.filter.tags="@negative"
./gradlew.bat test -Dcucumber.filter.tags="@auth"
```

## Reportes generados

Tras ejecutar la suite, Gradle genera reportes en la carpeta `build/reports`:

- `build/reports/cucumber/cucumber-report.html`
- `build/reports/cucumber/cucumber.json`
- `build/reports/cucumber/cucumber.xml`
- `build/reports/tests/test/index.html`

Para abrir el reporte HTML en Windows:

```powershell
Start-Process "build\reports\cucumber\cucumber-report.html"
```
