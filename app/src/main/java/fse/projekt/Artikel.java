package fse.projekt;

public class Artikel {
    private static int aktuelleID = 1; // Statische Variable zur ID-Verwaltung
    private int id;
    private String name;
    private int bestand;
    private double preis;

    // Konstruktor, der die ID automatisch vergibt
    public Artikel(String name, int bestand, double preis) {
        if(preis < 0){
            throw new IllegalArgumentException("Der Preis darf nicht kleiner oder gleich 0 sein.");
        }
        if(name == null){
            throw new IllegalArgumentException("Der Artikel muss einen Namen haben.");
        }
        if(bestand < 0){
            throw new IllegalArgumentException("Der Bestand kann nicht kleiner als 0 sein.");
        }

        this.id = aktuelleID++; // Automatische ID-Zuweisung
        this.name = name;
        this.bestand = bestand;
        this.preis = preis;
    }

    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getBestand(){
        return this.bestand;
    }

    public void setBestand(int bestand){
        this.bestand = bestand;
    }

    public double getPreis(){
        return this.preis;
    }

    public void setPreis(double preis){
        this.preis = preis;
    }
}
