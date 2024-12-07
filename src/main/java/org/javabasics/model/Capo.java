package org.javabasics.model;

import java.time.LocalDate;

public class Capo extends Model {

    private LocalDate dataArrivo;
    private String tipologia;
    private String marca;
    private String taglia;
    private Float prezzo;
    private Boolean disponibile;

    public Capo() {}

    public Capo(Integer id, LocalDate arrival, String type, String brand, String size, Float price, Boolean available)
    {
        super.id= id;
        dataArrivo= arrival;
        tipologia= type;
        marca= brand;
        taglia= size;
        prezzo= price;
        disponibile= available;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Data Inserimento: " + dataArrivo + "; Tipologia: " + tipologia + "; Marca: " + marca + "; Taglia: " + taglia + ";  Prezzo: " + prezzo + " €;";
    }

    public void stampa() {
        String risposta;
        if(disponibile) risposta="Sì!";
        else risposta= "No!";
        System.out.println(this + " Disponibile: " + risposta);
    }

    public String export() {
        return id + ";" + dataArrivo + "; " + tipologia + ";" + marca + ";" + taglia + ";" + prezzo + " €;SI;\n";
    }

    public Boolean isAvailable() {
        return disponibile;
    }

    public void setAvailability(Boolean answer) {
        disponibile= answer;
    }
}
