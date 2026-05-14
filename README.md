# PasswordChkr

PasswordChkr is a small desktop application written in Java with JavaFX that evaluates password strength and can generate secure passwords. The project implements dictionary checks, entropy calculation, basic NIST-inspired validation rules, and a configurable password generator.

## Features
- Password strength scoring and user feedback
- Dictionary-based weak-password detection using `weak_passwords.txt`
- Entropy calculation for provided passwords
- Basic NIST-style validations (length, character classes, etc.)
- Configurable password generator (length, include/exclude character classes)
- JavaFX user interface defined in `main-view.fxml` with styles in `style.css`

## Project structure (important files)
- `pom.xml` — Maven build configuration
- `src/main/java/module-info.java` — Java module descriptor
- `src/main/java/com/passwordchkr/Main.java` — application entry point
- `src/main/java/com/passwordchkr/controller/MainController.java` — JavaFX controller
- `src/main/java/com/passwordchkr/service/` — core services:
  - `DictionaryChecker.java` — checks passwords against `weak_passwords.txt`
  - `EntropyCalculator.java` — calculates password entropy
  - `NistValidator.java` — implements basic NIST-style checks
  - `PasswordGenerator.java` — generates random passwords
  - `PasswordStrengthService.java` — orchestrates checks and scoring
- `src/main/resources/com/passwordchkr/` — resources (FXML, CSS, weak password list)

## Requirements
- JDK 11 or newer (project uses Java modules; adapt to your JDK if needed)
- JavaFX SDK if your JDK does not bundle JavaFX (OpenJFX)
- Maven (project includes Maven wrapper `mvnw` / `mvnw.cmd`)

## Building
Open PowerShell in the project root (where `mvnw.cmd` is located) and run:

```powershell
.\mvnw.cmd clean package
```

This will compile sources and place classes in `target/classes` (and create a packaged artifact if configured in `pom.xml`).

## Running

1) Run from IDE (recommended during development)
- Import the project as a Maven/Java project in IntelliJ IDEA or another IDE and run `com.passwordchkr.Main`.

2) Run from command line with JavaFX SDK
- If you use a separate JavaFX SDK, download the appropriate OpenJFX distribution and point `--module-path` to its `lib` folder. Example (PowerShell):

```powershell
$env:PATH_TO_FX = 'C:\path\to\javafx-sdk-XX\lib'
java --module-path $env:PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp target\classes com.passwordchkr.Main
```

Adjust paths and module options according to your environment. If you package a runnable JAR with dependencies, run that JAR instead.

## Usage
- Launch the application and enter a password to receive a strength score and detailed feedback (entropy, rule violations, dictionary match warnings).
- Use the password generator to create random passwords with options for length and character sets.

## Configuration and data
- `src/main/resources/com/passwordchkr/weak_passwords.txt` — a newline-delimited list of common/weak passwords used by `DictionaryChecker`. Update this file to customize the dictionary.
- `main-view.fxml` and `style.css` control the UI layout and appearance.

## Security notes
- This tool provides local, client-side password strength feedback and is not a replacement for organizational password policies or security audits.
- Never commit real or sensitive passwords to version control. Avoid logging plaintext passwords.


