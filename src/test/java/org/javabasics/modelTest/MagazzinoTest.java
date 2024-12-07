package org.javabasics.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.javabasics.model.Capo;
import org.javabasics.model.Magazzino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MagazzinoTest {

    private Magazzino magazzino;

    @BeforeEach
    public void setUp() {
        magazzino = new Magazzino();
    }

    @Test
    public void testLoadFromCSV() {
        // Carica i dati dal file CSV tramite classpath
        String [] filePath = {"assets/capi.csv", "assets/utenti.csv", "assets/vendite.csv"};
    
        for (int i=0; i<3; i++)
        magazzino.loadFromCSV(filePath[i]);
    
        // Verifica che i dati siano stati caricati correttamente
        assertEquals(5, magazzino.capi.size(), "Il numero di capi caricati non è corretto");
        assertEquals(4, magazzino.utenti.size(), "Il numero di utenti caricati non è corretto");
        assertEquals(2, magazzino.vendite.size(), "Il numero di vendite caricate non è corretto");
    }
    

    @Test
    public void testEsportaCapiDisponibili() {
        // Aggiungi alcuni capi al magazzino
        Capo capo1 = new Capo(magazzino.capi.size()+1, LocalDate.now(), "Maglia", "Nike", "M", 29.99f, true);
        Capo capo2 = new Capo(magazzino.capi.size()+2, LocalDate.now(), "Pantaloni", "Adidas", "L", 49.99f, false);
        Capo capo3 = new Capo(magazzino.capi.size()+3, LocalDate.now(), "Scarpe", "Puma", "42", 79.99f, true);
        magazzino.capi.add(capo1);
        magazzino.capi.add(capo2);
        magazzino.capi.add(capo3);

        // Esporta i capi disponibili
        ArrayList<Capo> capiDisponibili = new ArrayList<>();
        for (Capo capo : magazzino.capi) {
            if (capo.isAvailable()) {
                capiDisponibili.add(capo);
            }
        }
        magazzino.esportaCapiDisponibili(capiDisponibili);

        // Verifica che il file CSV sia stato creato correttamente
        LocalDate oggi = LocalDate.now();
        String nomeFile = "capi_" + oggi.format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".csv";
        File file = new File(nomeFile);
        assertTrue(file.exists(), "Il file CSV non è stato creato");

        // Elimina il file CSV di test
        if (file.delete())
            System.out.println("File eliminato con successo: " + nomeFile);
        else fail("Errore durante l'eliminazione del file: " + nomeFile);
    }
}
