package fse.projekt;

import fse.projekt.Artikel;
import fse.projekt.Datenbankanbindung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AService {

    // Abrufen aller Artikel
    public static List<Artikel> alleArtikelAbrufen() {
        List<Artikel> artikel = new ArrayList<>();
        try (Connection conn = Datenbankanbindung.getConnection()) {
            String query = "SELECT * FROM Inventar";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                Artikel item = new Artikel(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("bestand"),
                        result.getDouble("preis")
                );
                artikel.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Artikel: " + e.getMessage());
        }
        return artikel;
    }


    // Abrufen eines spezifischen Artikels über die ID
    public static Artikel einzelnenArtikelAbrufen(int id) {
        Artikel artikel = null;
        try (Connection conn = Datenbankanbindung.getConnection()) {
            String query = "SELECT * FROM Inventar WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                artikel = new Artikel(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("bestand"),
                        result.getDouble("preis")
                );
            } else {
                throw new IllegalArgumentException("Artikel mit der ID " + id + " wurde nicht gefunden.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen des Artikels: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return artikel;
    }


    // Hinzufügen eines neuen Artikels
    public static void artikelHinzufügen(Artikel artikel) {
        try (Connection conn = Datenbankanbindung.getConnection()) {
            String query = "INSERT INTO Inventar (name, bestand, preis) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, artikel.getName());
            stmt.setInt(2, artikel.getBestand());
            stmt.setDouble(3, artikel.getPreis());

            stmt.executeUpdate();

            System.out.println("Artikel hinzugefügt: " + artikel.getName());

        } catch (SQLException e) {
            System.out.println("Fehler beim Hinzufügen des Artikels: " + e.getMessage());
        }
    }


    // Aktualisieren eines Artikels
    public static void artikelAktualisieren(int id, int neuerBestand, double neuerPreis) {
        try (Connection conn = Datenbankanbindung.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Inventar SET bestand = ?, preis = ? WHERE id = ?");
            stmt.setInt(1, neuerBestand);
            stmt.setDouble(2, neuerPreis);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new IllegalArgumentException("Artikel mit der ID " + id + " wurde nicht gefunden.");
            }

            System.out.println("Artikel aktualisiert: ID " + id);

        } catch (SQLException e) {
            System.out.println("Fehler beim Aktualisieren des Artikels: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
