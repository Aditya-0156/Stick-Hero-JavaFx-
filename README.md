# Stick Hero - JavaFX

A recreation of the popular mobile game **Stick Hero** built with JavaFX as a college project.

The player controls a character standing on platforms and must extend a stick to bridge the gap to the next platform. If the stick is too short or too long, the character falls and the game ends.

## Features

- **Multiple Characters** — Wizard, Archer, and Swordsman with sprite-based walk animations
- **Random Character Mode** — Toggle from settings to get a random character each round
- **Cherry Collectibles** — Earn cherries based on your score
- **Save & Load** — Save your current game and resume later
- **High Score Tracking** — Your best score is saved across sessions
- **Sound Effects** — Victory and game over audio feedback
- **Revive System** — Every 7 points earns you a free revive

## Design Patterns Used

- **Singleton** — `Stick` class uses a singleton pattern
- **Factory** — `SceneFactory` creates different scene switchers
- **Polymorphism** — `Switcher` interface with `SceneSwitch`, `SceneSwitch2`, `SceneSwitch3` implementations

## Project Structure

```
src/main/java/com/example/sh/
├── StickStart.java              # Application entry point
├── StickStartController.java    # Main menu controller
├── StartController.java         # Game start screen controller
├── GameController.java          # Core game logic
├── SettingsController.java      # Settings screen controller
├── Character.java               # Character with sprite animation
├── Cherry.java                  # Cherry collectible
├── GameRectangle.java           # Platform rectangles
├── Stick.java                   # Stick (Singleton)
├── SceneSwitch.java             # Scene switching + Factory
├── SceneSwitch2.java            # Scene switching variants
├── Randomizer.java              # Random character toggle
├── Stats.java                   # Stats (placeholder)
└── ParametrizedController.java  # Controller interface for passing params

src/main/resources/
├── com/example/sh/              # FXML layout files
│   ├── StickStart.fxml
│   ├── start.fxml
│   ├── game.fxml
│   └── settings.fxml
├── *.png                        # Character sprites & UI buttons
├── *.mp3                        # Sound effects
└── *.txt                        # Default game data
```

## Prerequisites

- **Java 18** or higher
- **Maven** (or use the included Maven wrapper)

## How to Run

```bash
# Clone the repository
git clone https://github.com/Aditya-0156/Stick-Hero-JavaFx-.git
cd Stick-Hero-JavaFx-

# Run with Maven
./mvnw clean javafx:run
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## How to Play

1. Click **Start** on the main menu
2. Choose **Play** for a new game or **Replay** to load a saved game
3. **Press and hold** the mouse to grow the stick
4. **Release** to drop the stick onto the next platform
5. If the stick reaches the platform, you score a point and move forward
6. If it misses, game over!

## Built With

- Java 18
- JavaFX 18
- Maven

## Authors

- Aditya Yadav
- Siddharth
