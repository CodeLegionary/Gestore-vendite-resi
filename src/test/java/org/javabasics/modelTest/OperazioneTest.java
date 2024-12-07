package org.javabasics.modelTest;

import static org.junit.jupiter.api.Assertions.*;
import org.javabasics.model.Operazione;
import org.javabasics.model.Magazzino;
import org.javabasics.model.Capo;
import org.javabasics.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OperazioneTest {

    private Magazzino magazzino;

    @BeforeEach
    public void setUp() {
        Service.resetInstance();
        magazzino = Service.getMagazzino();
    }

    @Test
    public void testPanoramica() {
        //Simulazione con ulteriori capi
        Capo capo1 = new Capo(magazzino.capi.size()+1, LocalDate.now(), "Maglia", "Nike", "M", 29.99f, true);
        Capo capo2 = new Capo(magazzino.capi.size()+2, LocalDate.now(), "Pantaloni", "Adidas", "L", 49.99f, false);
        magazzino.capi.add(capo1);
        magazzino.capi.add(capo2);

        Operazione.PANORAMICA.esegui();
    }

    @Test
    public void testEsportazione() {
        // Aggiungi alcuni capi al magazzino
        Capo capo1 = new Capo(magazzino.capi.size()+1, LocalDate.now(), "Maglia", "Nike", "M", 29.99f, true);
        Capo capo2 = new Capo(magazzino.capi.size()+2, LocalDate.now(), "Pantaloni", "Adidas", "L", 49.99f, false);
        magazzino.capi.add(capo1);
        magazzino.capi.add(capo2);

        // Esegui l'operazione ESPORTAZIONE
        Operazione.ESPORTAZIONE.esegui();

        // Verifica che il file CSV sia stato creato correttamente
        LocalDate oggi = LocalDate.now();
        String nomeFile = "capi_" + oggi.format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".csv";
        File file = new File(nomeFile);
        assertTrue(file.exists(), "Il file CSV non è stato creato");

        // Elimina il file CSV di test
        if (file.delete()) {
            System.out.println("File eliminato con successo: " + nomeFile);
        } else {
            fail("Errore durante l'eliminazione del file: " + nomeFile);
        }
    }
}
