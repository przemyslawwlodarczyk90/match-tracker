# Match Tracker

Konsolowa tablica wyników meczów piłkarskich. Ćwiczenie w Javie z prowadzenia listy aktywnych spotkań i testowania jej reguł.

## Jak działa tablica

- Rozpoczynasz mecz od wyniku 0:0 i aktualizujesz wynik w trakcie gry.
- Drużyna może brać udział w jednym aktywnym spotkaniu naraz.
- Podsumowanie pokazuje najpierw mecze z największą sumą bramek. Przy remisie wyżej jest spotkanie rozpoczęte później.
- Zakończenie meczu usuwa go z tablicy. Dane są przechowywane w pamięci na czas działania programu.

## Uruchomienie

Potrzebujesz JDK 21 i Mavena.

```bash
mvn compile
java -cp target/classes org.example.Main
```

Polecenie `mvn test` uruchamia testy reguł tablicy i akcji menu.
