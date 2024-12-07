package org.javabasics.model;

import java.time.LocalDate;

public class Utente extends Model {
    
    private String nome;
    private String cognome;
    private LocalDate nascita;
    private String indirizzo;
    private String documentId;

    public Utente() {}

    public Utente(Integer id, String nome, String cognome, LocalDate data, String indirizzo, String documento)
    {
        super.id= id;
        this.nome= nome;
        this.cognome= cognome;
        nascita= data;
        this.indirizzo= indirizzo;
        documentId= documento;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
}