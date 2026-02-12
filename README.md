# 💣 Bomberman - Java Game Engine & Custom Data Structures

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![Testing](https://img.shields.io/badge/Testing-JUnit%205-green?style=for-the-badge&logo=junit5)
![Architecture](https://img.shields.io/badge/Design-Patterns-blue?style=for-the-badge)

## 📖 About The Project

This project is a sophisticated implementation of the classic **Bomberman** game, developed as part of the "Programming 2" university module.

Going beyond simple game logic, this project focuses on **advanced software engineering concepts**. Instead of relying on standard Java Collections for everything, we implemented **custom data structures** (Vector, Doubly Linked List) from scratch to understand memory management and algorithmic efficiency. The game features a modular architecture with a **Console UI**, robust **Exception Handling**, and dynamic **Level Generation** using Java Reflection.

### ✨ Key Technical Features
* **Custom Collections Framework:** Implemented generic `Vector<T>` and `DoubleLinkedList<T>` with custom Iterators to manage game objects efficiently.
* **Game Loop Architecture:** Separation of `GameState` (Data), `GameImpl` (Logic), and `UI` (View) allows for decoupled development.
* **Java Reflection:** Dynamic loading of Level Generators (`LevelGeneratorLoader`) at runtime, allowing new level types to be added without recompiling the core engine.
* **Advanced Exception Handling:** Custom RuntimeExceptions (`IllegalMoveException`, `NoMoreBombsAllowed`) to enforce game rules strictly.
* **Unit Testing:** Comprehensive **JUnit 5** test suite validating movement logic and custom container integrity.

---

## 🏗️ Architecture

The project follows a strict package-based modular design:

```text
src/de/tha/prog2/praktikum/game/
├── container/       # Custom Data Structures (Vector, LinkedList)
├── core/            # Core Engine (Game Loop, Board, State)
├── gameobjects/     # Entities (Bomberman, Monster, Bomb, PowerUps)
├── level/           # Procedural Level Generation & Reflection Loader
├── ui/              # User Interface (Console Rendering)
├── exceptions/      # Custom Error Handling
└── tests/           # JUnit Test Suite
````

---

## Deep Dive: Custom Data Structures

We avoided `java.util.ArrayList` or `java.util.LinkedList` for the core game logic to demonstrate understanding of algorithmic complexity:

  * `Vector<T>`: A dynamic array implementation with automatic resizing and index-based access.
  * `DoubleLinkedList<T>`: A node-based structure allowing efficient insertion/deletion at both ends, used for managing the `GameObjectSet`.

## Deep Dive: Level Generation

Levels are not hardcoded. The `LevelGeneratorLoader` uses Reflection (`Class.forName()`) to instantiate generators like `BasicGenerator` or `SymmetricalLevelGenerator` based on a string input. This makes the engine highly extensible.

---

## 🕹️ Gameplay & Mechanics

The game runs in the console using ASCII art rendering.

**1. Controls (Console Input)**

  * `w`: Move Up

  * `a`: Move Left

  * `s`: Move Down

  * `d`: Move Right

  * `b`: BOMB (Place a bomb at current location)

**2. Game Objects**

  * `B` (Bomberman): The player character.

  * `m` (Monster): Randomly moving enemies.

  * `o` (Bomb): Explodes after 5 ticks, creating a cross-shaped blast (+).

  * `W` (Wall): Indestructible barriers.

  * `X` (Destructible Wall): Can be destroyed to reveal Power-Ups.

  * Power-Ups:

    1: Extend Bomb Blast Radius

    2: Increase Max Bomb Count

    3: Extra Points

---

## 🚀 How to Run

**Prerequisites**

  * Java JDK 17 or higher.

**Execution**

  1. Clone the repository:
  ```bash
  git clone [https://github.com/mlrlouis/Bomberman.git](https://github.com/mlrlouis/Bomberman.git)
  ```
  
  2. Compile the source:
  ```bash
    javac -d bin src/de/tha/prog2/praktikum/game/**/*.java
  ```
  
  3.Run the Game:
  ```bash
  java -cp bin de.tha.prog2.praktikum.game.Launcher
  ```

---

## 🧪 Testing

The project includes a robust test suite using JUnit 5.

  * `ContainerTest`: Verifies the integrity of `Vector` and `LinkedList` (add, remove, resize, iterate).

  * `MovementTest`: Validates collision detection and coordinate logic (`XY` class).

To run the tests (ensure JUnit 5 is in your classpath):

```bash
# Example command (depends on your JUnit setup)
java -jar junit-platform-console-standalone.jar -cp bin --scan-classpath
```

---

## 👨‍💻 Team

* Louis Müller

* [Partner]

<p align="right">(<a href="#top">back to top</a>)</p>
