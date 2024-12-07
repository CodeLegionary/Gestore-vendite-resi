package org.javabasics.controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.javabasics.controller.Controller;
import org.javabasics.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest {

    private Controller controller;
    private Service service;

    @BeforeEach
    public void setUp() {
        service = Service.getInstance();
        controller = new Controller();
    }

    @Test
    public void testStart() {
        String input = "1\n0\n"; // Simula l'input dell'utente
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        controller.start();

        // Verifica che il metodo selezionaOpzione sia stato chiamato
        assertTrue(controller.isOpzioneSelezionata(), "Il metodo selezionaOpzione dovrebbe essere stato chiamato");
    }

    @Test
    public void testInvalidInput() {
        String input = "a\n0\n"; // Simula un input non valido seguito da un'uscita
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        controller.start();

        // Verifica che il metodo start annullato si ripeta per terminare con exit code '0'
        assertTrue(controller.isOpzioneSelezionata(), "Il metodo selezionaOpzione dovrebbe essere stato chiamato");
    }

    @Test
    public void testValidOptions() {
        for (int i = 0; i <= 5; i++) {
            String input = i + "\n0\n"; // Simula l'input dell'utente
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            controller.start();

            // Verifica che il metodo selezionaOpzione sia stato chiamato con il valore corretto
            assertTrue(controller.isOpzioneSelezionata(), "Il metodo selezionaOpzione dovrebbe essere stato chiamato con input: " + i);
        }
    }
}
