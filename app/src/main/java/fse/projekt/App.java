package fse.projekt;

import fse.projekt.Artikel;
import fse.projekt.AService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                // Hauptmenü anzeigen
                System.out.println("\nWähle eine Option:");
                System.out.println("1: Alle Artikel anzeigen");
                System.out.println("2: Artikel nach ID suchen");
                System.out.println("3: Neuen Artikel hinzufügen");
                System.out.println("4: Artikel aktualisieren");
                System.out.println("0: Beenden");
                System.out.print("Deine Auswahl: ");

                // Auswahl lesen
                String input = reader.readLine();
                int option = Integer.parseInt(input);

                switch (option) {
                    case 1 -> {
                        // Alle Artikel anzeigen
                        List<Artikel> artikelListe = AService.alleArtikelAbrufen();
                        if (artikelListe.isEmpty()) {
                            System.out.println("Keine Artikel gefunden.");
                        } else {
                            artikelListe.forEach(artikel -> System.out.println(
                                    "ID: " + artikel.getID() +
                                    ", Name: " + artikel.getName() +
                                    ", Bestand: " + artikel.getBestand() +
                                    ", Preis: " + artikel.getPreis()
                            ));
                        }
                    }
                    case 2 -> {
                        // Artikel nach ID suchen
                        System.out.print("Gib die ID des Artikels ein: ");
                        String idInput = reader.readLine();
                        int id = Integer.parseInt(idInput);
                        Artikel artikel = AService.einzelnenArtikelAbrufen(id);
                        if (artikel != null) {
                            System.out.println("ID: " + artikel.getID() +
                                    ", Name: " + artikel.getName() +
                                    ", Bestand: " + artikel.getBestand() +
                                    ", Preis: " + artikel.getPreis());
                        } else {
                            System.out.println("Kein Artikel mit dieser ID gefunden.");
                        }
                    }
                    case 3 -> {
                        // Neuen Artikel hinzufügen
                        System.out.print("Name: ");
                        String name = reader.readLine();

                        System.out.print("Bestand: ");
                        String bestandInput = reader.readLine();
                        int bestand = Integer.parseInt(bestandInput);

                        System.out.print("Preis: ");
                        String preisInput = reader.readLine();
                        double preis = Double.parseDouble(preisInput);

                        Artikel neuerArtikel = new Artikel(0, name, bestand, preis);
                        AService.artikelHinzufügen(neuerArtikel);
                        System.out.println("Artikel erfolgreich hinzugefügt.");
                    }
                    case 4 -> {
                        // Artikel aktualisieren
                        System.out.print("Gib die ID des Artikels ein: ");
                        String idInput = reader.readLine();
                        int id = Integer.parseInt(idInput);

                        System.out.print("Neuer Bestand: ");
                        String neuerBestandInput = reader.readLine();
                        int neuerBestand = Integer.parseInt(neuerBestandInput);

                        System.out.print("Neuer Preis: ");
                        String neuerPreisInput = reader.readLine();
                        double neuerPreis = Double.parseDouble(neuerPreisInput);

                        AService.artikelAktualisieren(id, neuerBestand, neuerPreis);
                        System.out.println("Artikel erfolgreich aktualisiert.");
                    }
                    case 0 -> {
                        // Programm beenden
                        System.out.println("Programm wird beendet...");
                        return;
                    }
                    default -> System.out.println("Ungültige Auswahl. Bitte gib eine Zahl zwischen 0 und 4 ein.");
                }
            } catch (IOException e) {
                System.out.println("Ein Fehler ist bei der Eingabe aufgetreten: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte gib eine Zahl ein.");
            } catch (IllegalArgumentException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }
}