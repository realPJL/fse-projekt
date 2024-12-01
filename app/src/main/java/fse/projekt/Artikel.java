package fse.projekt;

public class Artikel {
    private int id;
    private String name;
    private int bestand;
    private double preis;

    public Artikel(int id, String name, int bestand, double preis){
        if(id <= 0){
            throw new IllegalArgumentException("Die ID darf nicht kleiner oder gleich 0 sein.");
        }
        if(preis < 0){
            throw new IllegalArgumentException("Der Preis darf nicht kleiner oder gleich 0 sein.");
        }
        if(name == null){
            throw new IllegalArgumentException("Der Artikel muss einen Namen haben.");
        }
        if(bestand < 0){
            throw new IllegalArgumentException("Der Bestand kann nicht kleiner als 0 sein.");
        }

        this.id = id;
        this.name = name;
        this.bestand = bestand;
        this.preis = preis;
    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
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
