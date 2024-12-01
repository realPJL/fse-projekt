package fse.projekt;

import fse.projekt.Artikel;
import fse.projekt.AService;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Wähle eine Option:");
            System.out.println("1: Alle Artikel anzeigen");
            System.out.println("2: Artikel nach ID suchen");
            System.out.println("3: Neuen Artikel hinzufügen");
            System.out.println("4: Artikel aktualisieren");
            System.out.println("0: Beenden");

            int option = scanner.nextInt();
            scanner.nextLine(); // Konsolenpuffer leeren, also die 1,2,3,4 oder 0 aus option leeren

            switch (option) {
                case 1 -> {
                    List<Artikel> neuerArtikel = AService.alleArtikelAbrufen();
                    neuerArtikel.forEach(artikel -> System.out.println("ID: " + artikel.getID() + ", Name: " + artikel.getName() + ", Bestand: " + artikel.getBestand() + ", Preis: " + artikel.getPreis()));
                }
                case 2 -> {
                    System.out.print("Gib die ID des Artikels ein: ");
                    int id = scanner.nextInt();
                    Artikel artikel = AService.einzelnenArtikelAbrufen(id);
                    if (artikel != null) {
                        System.out.println("ID: " + artikel.getID() + ", Name: " + artikel.getName() +", Bestand: " + artikel.getBestand() + ", Preis: " + artikel.getPreis());
                    }
                }
                case 3 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Bestand: ");
                    int bestand = scanner.nextInt();
                    System.out.print("Preis: ");
                    double preis = scanner.nextDouble();
                    
                    try {
                        Artikel neuArtikel = new Artikel(0, name, bestand, preis);

                        AService.artikelHinzufügen(neuArtikel);

                    } catch (IllegalArgumentException e) {
                        System.out.println("Fehler: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("Gib die ID des Artikels ein: ");
                    int id = scanner.nextInt();
                    System.out.print("Neuer Bestand: ");
                    int neuerBestand = scanner.nextInt();
                    System.out.print("Neuer Preis: ");
                    double neuerPreis = scanner.nextDouble();

                    AService.artikelAktualisieren(id, neuerBestand, neuerPreis);
                }
                case 0 -> {
                    System.out.println("Beende Programm...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Ungültige Option. Bitte erneut versuchen.");
            }
        }
    }
}

