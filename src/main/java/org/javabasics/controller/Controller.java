package org.javabasics.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.javabasics.service.Service;

public class Controller
{
    // interfaccia controllore
    //prima interfaccia utente
    private Service service;
    private boolean opzioneSelezionata= false;

    public Controller() {
        service = Service.getInstance();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int input1 = -1;

        while (input1 != 0) {
            mostraOpzioni();

            try {
                input1 = scanner.nextInt();
                while (input1 < 0 || input1 > 5) {
                    System.out.print("Digita un numero valido ");
                    input1 = scanner.nextInt();
                }
                service.selezionaOpzione(input1);
                opzioneSelezionata = true; //ai fini del TESTING mirato
            } catch (InputMismatchException e) {
                System.out.println("Errore: input non valido.");
                scanner.next(); // Consuma l'input non valido
            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
            }
        }
        scanner.close();
    }


        private void mostraOpzioni() {
        System.out.println("\nDigitare un numero per selezionare  un'operazione dalla lista:\n");
        System.out.println("1: Visualizzare tutti i capi second hand all'interno del sistema");
        System.out.println("2: Comprare un capo esistente");
        System.out.println("3: Restituire un capo");
        System.out.println("4: Aggiungere nuovo utente");
        System.out.println("5: Esportare un file con i capi disponibili");
        System.out.println("0: Uscire dal programma");
    }

    public boolean isOpzioneSelezionata() {
        return opzioneSelezionata; }
}
