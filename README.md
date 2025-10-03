# 🎯  PROP-Project — Mastermind Game

<sub>🗓️ Developed in June 2023</sup>

This project is an implementation of the well-known game **Mastermind**, developed following the **three-layer architecture** design pattern (**Presentation**, **Domain**, and **Persistence** layers).

---

## ✅ Features

- **Domain logic** with Five-Guess & Genetic algorithms to solve the game.
- **Persistence layer** for storing data.
- **Presentation layer** for interacting with the user using **GUI Swing**.
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

### 3. Use the `/DOCS/User Manual.pdf` to learn how to play.

---

## 🧪 Running Tests

JUnit test cases are provided for all major classes.  
However, these are **designed to run on Linux/macOS environments** due to differences in classpath handling and file encoding on Windows. If you are using Windows, you may encounter compilation errors with JUnit or encoding issues.

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

You can also run **driver_algorisme** to test algorithm-specific code (works on all environments):

```bash
make driver_algorisme
```

---

## 📂 Files

The project is organized into the following directories:

### `/lib`
Contains all required external Java libraries:
- `byte-buddy-1.12.16.jar`
- `byte-buddy-agent-1.12.16.jar`
- `gson-2.8.5.jar`
- `hamcrest-core-1.3.jar`
- `junit-4.12.jar`
- `mockito-core-4.9.0.jar`
- `objenesis-3.3.jar`

### `/EXE`
Contains compiled **JAR** files and output data.

### `/FONTS`
Contains the **source code**, the **Makefile**, and all **test cases**.

### `/DOCS`
Contains all project documentation, organized by delivery:

#### 📦 1st Delivery
- **Use cases**
- **Conceptual model diagram**
- **Data structures and algorithms used** (*Five Guess* algorithm)

#### 📦 2nd Delivery
- **Presentation layer**
- **Domain layer**
- **Persistence layer**
- **Data structures and algorithms used** (*Genetic Algorithm*)

#### 📦 3rd Delivery
- **User manual**
- **Test cases**

---

## 📷 Screenshots  

### Main Page:
![Captura de pantalla 2025-08-13 160230](https://github.com/user-attachments/assets/fa5492c8-3519-4655-9c59-436a156a91ec)
-
### Register Page:
![Captura de pantalla 2025-08-13 160343](https://github.com/user-attachments/assets/b7d24550-17f1-47ae-80ad-2a078e8e1fb8)
-
### Login Page:
![Captura de pantalla 2025-08-13 160438](https://github.com/user-attachments/assets/4e3afce7-9116-4db6-a88e-9c2b91a85396)
-
### Main Menu Page:
![Captura de pantalla 2025-08-13 161141](https://github.com/user-attachments/assets/53905e06-cf5c-401f-9bad-ee5ad69d8e85)
-
### Play Page - Configurations:
![Captura de pantalla 2025-08-13 161304](https://github.com/user-attachments/assets/f021565a-99f5-4572-b8fe-a816db57601e)  

![Captura de pantalla 2025-08-13 161503](https://github.com/user-attachments/assets/9a01a6b0-5883-417f-a2a7-09f294dbd93d)  

![Captura de pantalla 2025-08-13 162000](https://github.com/user-attachments/assets/aac216c3-6588-48ab-b88f-492313e617ed)  

![Captura de pantalla 2025-08-13 161808](https://github.com/user-attachments/assets/4978f70c-9a77-4459-aafd-6a6141b27d91)  
-
### Load Game Page:
![image5](https://github.com/user-attachments/assets/bd7ab068-fca7-46bc-baeb-453a06601f74)  
-
### Record Page:
![Captura de pantalla 2025-08-13 162730](https://github.com/user-attachments/assets/9ad19467-2992-426c-8885-c134b517e041)  
-
### Ranking Page:
![Captura de pantalla 2025-08-13 162834](https://github.com/user-attachments/assets/bfe23d78-94a3-46c3-8d0b-096be4fe3a33)  

![image6](https://github.com/user-attachments/assets/3a176b27-16ef-4a28-85fd-d186a71dcb70)  

![image12](https://github.com/user-attachments/assets/e0431baa-8cda-44e2-8ba4-5843eed2c288)  

![image14](https://github.com/user-attachments/assets/8ac08a47-6b46-4c20-94ee-8480ace3f80f)  
-
### User Page:
![Captura de pantalla 2025-08-13 163741](https://github.com/user-attachments/assets/43a884c9-f221-4c14-a7fa-d923df8c8fff)  

#### Stats Page:
![Captura de pantalla 2025-08-13 163852](https://github.com/user-attachments/assets/3d5ec9cc-b70b-4a03-bb78-bfae76ca7f6a)

#### Hisotry Page:
![Captura de pantalla 2025-08-13 164045](https://github.com/user-attachments/assets/dd965b6b-d121-4276-a0a3-b376cf63534e)  
-
### Game Page:
##### Example of the start of a game as Codemaker against the Genetic machine
![Captura de pantalla 2025-08-13 164757](https://github.com/user-attachments/assets/9df080a2-3ee2-47fe-b2ac-f9297ec4d37b)  

##### Example of the end of a game as Codemaker against the Genetic machine
![Captura de pantalla 2025-08-13 164655](https://github.com/user-attachments/assets/bf16db51-e94f-4bc0-9d9a-c3ccad9d6625)
-
### Additional panels
![Captura de pantalla 2025-08-13 165236](https://github.com/user-attachments/assets/1fa76d65-3965-4788-a4d6-d99250a04233)  

![Captura de pantalla 2025-08-13 165338](https://github.com/user-attachments/assets/458a3866-41ab-426f-a9c5-76c8d0159b8c)

---
### Use Cases Diagram:
![Diagram_UseCases](https://github.com/user-attachments/assets/31b41ec2-e0e4-420e-a7d0-33031364082a)

### UML:
![Diagram_UML](https://github.com/user-attachments/assets/7a057ba7-55c3-4c35-b0a8-f05d7f7d113b)  

### Presentation Layer:
![Layer_Presentation](https://github.com/user-attachments/assets/8419da9b-d5c2-4979-b474-8232e2c22230)

### Domain Layer:
![Layer_Domain](https://github.com/user-attachments/assets/8b861d75-c13d-4ff5-8f17-41a2a3ea1f6e)

### Persistence Layer:
![Layer_Persistence](https://github.com/user-attachments/assets/365c81a0-188d-488e-96dd-0e6613503fad)

---

## ⚖️ Copyright & License

© 2023 Marc Turu Roca and collaborators. All rights reserved.  
This project is the joint intellectual property of its authors.  
No part may be copied, modified, distributed, or used without prior written permission from all authors.  

- Ferran Solanes Serrat  
- Jordi Baranda Dominguez  
- Juan Clusellas Cánova    
- Marc Turu Roca

