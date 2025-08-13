# 🎯  PROP-Project — Mastermind

<sub>🗓️ Developed in June 2023</sup>

This project is an implementation of the **well-known game Mastermind**, developed following the **three-layer architecture** design pattern (**Presentation**, **Domain**, and **Persistence** layers).

---

## ✅ Features

- **Domain logic** with algorithms to solve the game.
- **Persistence layer** for storing data.
- **Presentation layer** for interacting with the user.
- **Comprehensive documentation** with UML diagrams, use cases, and test plans.

---

## 🛠 Installation & Setup

### 0. Prerequisites
Make sure you have installed:
- **Java 11** or higher (JDK)
- **GNU Make** (to run the Makefile commands)

You can check your versions with:
```bash
java -version
make --version
```

### 1. Clone the repository

```bash
git clone https://github.com/marcturu/PROP-Project.git
cd PROP-Project
```

### 2. Compile or execute the program  

Navigate to `/FONTS/` and use the provided **Makefile** commands:

```bash
# Recompile all Java files
make executable

# Run the program from the precompiled JAR
make executar
```

The program uses the `--add-opens java.base/java.time=ALL-UNNAMED` flag for Java module access.

---

## 🧪 Running Tests

JUnit test cases are provided for all major classes.

```bash
# Run all tests
make allTest

# Run a specific test
make TestRecord
make TestRecordInteger
make TestRecordDouble
make TestRecordLong
make TestRanking
make TestPartida
make TestRonda
make TestSequencia
make TestUser
make TestUser_maquina
make TestUser_persona
```

You can also run **driver_algorisme** to test algorithm-specific code:

```bash
make driver_algorisme
```

---

## 📊 Documentation

### Technical Documentation
Inside `/DOCS/`, you will find:
- UML **class diagrams**.
- **Use case diagrams**.
- **Layer architecture diagrams**.
- Descriptions of **data structures and algorithms**.
- **User manual**.

---

## 👥 Authors

- Jordi Baranda Dominguez  
- Juan Clusellas Cánova  
- Ferran Solanes Serrat  
- Marc Turu Roca  


