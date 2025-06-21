# ⚽️ Match Tracker

> **Zaawansowana aplikacja konsolowa do śledzenia wyników meczów piłkarskich**  
> **SOLID | SRP | Open/Closed | Fasada | TDD** 🚦

---

## 📖 O projekcie

**Match Tracker** to przejrzysty, testowalny system do zarządzania meczami piłkarskimi, napisany w duchu **Clean Code** i nowoczesnych wzorców projektowych.

Projekt powstał **w pełni zgodnie z filozofią TDD (Test-Driven Development)** — najpierw powstawały testy jednostkowe oraz integracyjne, dopiero potem właściwy kod produkcyjny.

---

## 🧩 Architektura i wzorce

- **Fasada (`ScoreBoardFacade`)**  
  Cała logika zarządzania meczami jest ukryta za prostym, jednoznacznym interfejsem. Dzięki temu kod menu i akcji konsolowych jest czysty, a testowanie proste.

- **SRP – Single Responsibility Principle**  
  Każda klasa odpowiada za jedną, konkretną rzecz. Handlery mają pojedyncze odpowiedzialności — osobno za rozpoczynanie meczu, aktualizację wyniku, kończenie meczu i pobieranie podsumowania.

- **Open/Closed Principle**  
  Dodanie nowej akcji, repozytorium czy widoku nie wymaga modyfikacji istniejącego kodu — po prostu implementujesz nową klasę i podłączasz.

- **TDD (Test-Driven Development)**  
  Każdy kluczowy komponent (fasada, handlery, akcje menu, repozytorium) powstał najpierw w testach (JUnit + AssertJ), potem w implementacji.  
  Pokrycie testami to nie pusty slogan — sprawdź folder z testami!  
  - Testy akcji konsolowych — symulacja realnej pracy użytkownika.
  - Testy handlerów i fasady — edge-case’y, wyjątki, reguły biznesowe.

- **Clean Code**  
  Prosta nawigacja, klasy podzielone wg przeznaczenia, przemyślane nazwy i odpowiedzialności.

---

## 🔍 Co potrafi aplikacja?

- **Start nowego meczu** (z weryfikacją, czy drużyna nie gra już w innym spotkaniu)
- **Aktualizacja wyniku** wskazanego meczu
- **Zakończenie meczu** (usuwanie z tablicy aktywnych spotkań)
- **Wyświetlanie podsumowania** (mecze posortowane: suma bramek, potem czas rozpoczęcia)
- **Bezpieczna obsługa wyjątków** (nie można rozpocząć dwóch meczów z tą samą drużyną itp.)

---

## 🗂️ Struktura projektu

```text
org.example
│
├── comparator      # Sortowanie podsumowania meczów
├── console         # Akcje i menu konsolowe
├── exception       # Własne wyjątki
├── facade          # Fasada, warstwa pośrednia
├── handler         # SRP: logika startu/aktualizacji/kończenia/podsumowania
├── model           # Klasa Match
├── repository      # Repozytoria (na razie in-memory, ale łatwo rozszerzyć)
│
└── Main.java       # Wejście do programu 
```

## 🏗️ Tok pracy: TDD w praktyce

1. **Red** – Najpierw piszemy test (np. `ScoreBoardFacadeTest`, `StartGameActionTest`).
2. **Green** – Pisanie minimalnej implementacji, żeby test przeszedł.
3. **Refactor** – Porządkujemy kod, rozbijamy na klasy zgodnie z SRP, poprawiamy czytelność.
4. **Repeat** – Kolejna funkcja — kolejny cykl test → kod → refaktor.

W commicie znajdziesz _prawdziwe TDD_:  
Testy do każdego handlera, fasady, klasy menu i akcji — wszystko z przykładowymi danymi, sprawdzane wyjątkowe sytuacje.

---

## 🏛️ Przykłady wzorców i SOLID

- **Fasada:**  
  ```java
  public class ScoreBoardFacade {
      public void startGame(String home, String away) { ... }
      public void updateScore(String home, String away, int h, int a) { ... }
      public void finishGame(String home, String away) { ... }
      public List<Match> getSummary() { ... }
  }

### 🟢 SRP (Single Responsibility Principle)

- **StartGameHandler** — tylko startuje mecz
- **UpdateScoreHandler** — tylko aktualizuje wynik
- **FinishGameHandler** — tylko kończy mecz
- **GetSummaryHandler** — tylko zwraca listę spotkań

---

### 🔓 Open/Closed Principle

Dodajesz nową akcję, np. **eksport do pliku** — nie zmieniasz istniejących klas, tylko tworzysz własną.

---

### 🖥️ Jak uruchomić?

1. **Zbuduj projekt Mavenem:**
    ```bash
    mvn clean install
    ```

2. **Odpal Main.java**  
   Możesz uruchomić z poziomu IDE (np. IntelliJ) albo z linii poleceń:
    ```bash
    java -cp target/match-tracker-1.0-SNAPSHOT.jar org.example.Main
    ```

---

## 📊 Przykład działania (pełny flow użytkownika)

```bash
FOOTBALL WORLD CUP SCOREBOARD

1. Start a new game
2. Update score  
3. Finish game  
4. Show summary  
5. Exit

Choose option: 1

Home team: England
Away team: Poland

Game started: England vs Poland

Choose option: 2

--- Choose a game to update score ---
1. England 0 - Poland 0

Enter game number: 1
New home score: 3
New away score: 2

Score updated for England vs Poland

Choose option: 4

--- Current matches (ordered by total score, then time) ---
1. England 3 - Poland 2

Choose option: 3

--- Choose a game to finish ---
1. England 3 - Poland 2

Enter game number: 1

Game finished: England vs Poland

Choose option: 4

No games currently on the scoreboard.

Choose option: 5

Bye!
```

---
## ✅ Testy

### Technologie
- **JUnit 5** + **AssertJ**

### Pokrycie testami
Testy jednostkowe i integracyjne obejmują:
- **Konsola**: pełny flow użytkownika
- **Fasada i handlery**: reguły biznesowe i edge-case'y

### Uruchomienie testów
```bash
mvn test
```

## 🧠 Wnioski i inspiracje

### Architektura
🔹 **Fasada** - pełna separacja interfejsu użytkownika od logiki biznesowej  
🔹 **SOLID** + **SRP** + **Open/Closed** - kod:
   - Rozszerzalny
   - Łatwy w utrzymaniu
   - Modularny

### Metodologia
✅ **TDD** zapewnia:
   - Lepszą architekturę
   - Wczesne wykrywanie błędów
   - Pewność działania systemu

### Rozwój projektu
🚀 Gotowy na rozbudowę o:
- [ ] Repozytoria (baza danych)
- [ ] REST API
- [ ] Warstwa frontendowa

...bez modyfikacji core'owej logiki!

---

⚽ **Stworzone z pasją do czystego kodu!**  
💡 *Przemysław Włodarczyk*
