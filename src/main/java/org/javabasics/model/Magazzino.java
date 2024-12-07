package org.javabasics.model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Magazzino {

    public ArrayList<Utente> utenti;
    public ArrayList<Capo> capi;
    public ArrayList<Vendita> vendite;

    public Magazzino() {
        utenti= new ArrayList<>();
        capi= new ArrayList<>();
        vendite= new ArrayList<>();
    }

    public void loadFromCSV(String filePath) {

        try {
            // Usa getResourceAsStream per ottenere un InputStream dal classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) throw new IOException("File non trovato nel classpath: ");

        BufferedReader br= new BufferedReader(new InputStreamReader(inputStream));
        
        br.readLine(); //reads line 0
        String riga= br.readLine(); //reads 1st line

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    while
    (riga.charAt(0)!=';')
        {

            String [] tokens= riga.split(";");

            if (tokens.length==3) {
                Vendita v= new Vendita(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                vendite.add(v);
            }
            else if (tokens.length==6)
            {
                Utente u= new Utente(Integer.parseInt(tokens[0]), tokens[1], tokens[2], LocalDate.parse(tokens[3], formatter), tokens[4], tokens[5]);
                utenti.add(u);
            }
            else
            {
                String priceString = tokens[5].replaceAll("[^\\d.]", "");
                Float price = Float.parseFloat(priceString);
                Capo c= new Capo(Integer.parseInt(tokens[0]), LocalDate.parse(tokens[1], formatter), tokens[2], tokens[3], tokens[4], price, (tokens[6].equalsIgnoreCase("SI") || tokens[6].equalsIgnoreCase("sì")));
                capi.add(c);
            }
            riga= br.readLine();
            if(riga==null) break;
        }

        br.close();
        } catch (IOException e) { System.out.println("Si è verificato un errore durante la lettura da file: " + e.getMessage());
    }
}

    public void esportaCapiDisponibili(ArrayList<Capo> clothes) {
        LocalDate oggi = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String nomeFile = "capi_" + oggi.format(formatter) + ".csv";

        try (FileWriter writer = new FileWriter(nomeFile)) {
            writer.append("ID;Data Inserimento;Tipologia;Marca;Taglia;Prezzo;Disponibile;\n");

            for (Capo capo : clothes) {
                if (capo.isAvailable()) {
                    writer.append(capo.export());
                }
            }

            System.out.println("File CSV creato con successo: " + nomeFile);
        } catch (IOException e) {
            System.out.println("Errore durante la creazione del file CSV: " + e.getMessage());
        }
    }

}