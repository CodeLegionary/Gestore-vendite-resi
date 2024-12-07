package org.javabasics.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.javabasics.service.Service;

public enum Operazione {
    PANORAMICA {
        @Override
        public void esegui() {
            System.out.println("Visualizzazione dei capi registrati nel sistema:");

            Magazzino magazzino= Service.getMagazzino();
            for(Capo veste : magazzino.capi) veste.stampa();
        }
    },

    ACQUISTO {
        @Override
        public void esegui() {
            Scanner scan = new Scanner(System.in);

            int input, input2;
            Magazzino magazzino = Service.getMagazzino();
            System.out.print("Hai optato per l'acquisto di un capo second hand, complimenti!\n Inizia inserendo ID del capo: ");

            try {
                input = scan.nextInt();

                if (magazzino.capi.get(input - 1).isAvailable()) {
                    System.out.print("Bene, per confermare immetti ID utente: ");
                    input2 = scan.nextInt();
                    while(input2>magazzino.utenti.size() || input2<=0)
                        {
                            System.out.println("Attenzione, immettere ID valido o qualsiasi lettera per annullare");
                            input2 = scan.nextInt();
                        }
                    if (input <= magazzino.capi.size() && input > 0) {
                        Vendita v = new Vendita(magazzino.vendite.size() + 1, input, input2);
                        magazzino.vendite.add(v);
                        magazzino.capi.get(input-1).setAvailability(false);
                        System.out.println("Perfetto: acquisto effettuato con successo!");
                    }
                } else System.out.println("Purtroppo il capo non è disponibile.");
            } catch (InputMismatchException e) {
                System.out.println("Errore: input non valido.");
            }
            catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
            }
        }
    },

    RESTITUZIONE {
        @Override
        public void esegui() {
            Magazzino magazzino = Service.getMagazzino();
            System.out.println("Per la restituzione di un capo, seguire la procedura");

            System.out.print("Immettere ID della vendita ");
            Scanner scan= new Scanner(System.in);
            int input = -1;

            try {
                input = scan.nextInt();

                while (input <= 0 || input > magazzino.vendite.size()) {
                    System.out.print("Digita un numero valido ");
                    input = scan.nextInt();
                }

                int idCapo= magazzino.vendite.get(input-1).getIdCapo();
                if(magazzino.capi.get(idCapo-1).isAvailable())
                    System.out.println("Restituzione fallita: il capo risulta attualmente disponibile");
                else {
                    magazzino.vendite.remove(input-1);
                    magazzino.capi.get(idCapo-1).setAvailability(true);
                    System.out.println("Restituzione avvenuta con successo!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: input non valido.");
                scan.next(); // Consuma l'input non valido
            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
            }
        }
    },

    REGISTRAZIONE {
        @Override
        public void esegui() {
            Scanner scan = new Scanner(System.in);
            String[] righe = new String[5];
            Arrays.fill(righe, "");

            System.out.println("Preparazione per l'aggiunta di un nuovo utente, seguire le istruzioni");

            Magazzino magazzino = Service.getMagazzino();
            int index = magazzino.utenti.size();
            String message= "Errore: il campo non può essere vuoto. Per favore, riprova.";
            String regex = "^[a-zA-Z\\s]+$"; // Solo lettere e spazi

            while (true) {
                System.out.print("Inserire nome: ");
                righe[0] = scan.nextLine();
                if (righe[0].isEmpty()) {
                    System.out.println(message);
                } else if (!righe[0].matches(regex)) {
                    System.out.println("Errore: il nome non può contenere numeri o caratteri speciali. Per favore, riprova.");
                } else {
                    break; // Esce dal ciclo se il nome è valido
                }
            }


            while (true) {
                System.out.print("Inserire cognome: ");
                righe[1] = scan.nextLine();
                if (righe[1].isEmpty()) {
                    System.out.println(message);
                } else if (!righe[1].matches(regex)) {
                    System.out.println("Errore: il cognome non può contenere numeri o caratteri speciali. Per favore, riprova.");
                } else {
                    break; // Esce dal ciclo se il nome è valido
                }
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            boolean validDate = false;
            LocalDate date = null;

            while (!validDate) {
                System.out.print("Inserire data di nascita nel formato yyyy-MM-dd ");
                righe[2] = scan.nextLine();

                try {
                    date = LocalDate.parse(righe[2], formatter);
                    validDate = true; // Esce dal ciclo se la data è valida
                } catch (DateTimeParseException e) {
                    System.out.println("Errore: formato data non valido. Assicurati di inserire la data nel formato yyyy-MM-dd.");
                }
            }

            while (righe[3].isEmpty()) {
                System.out.print("Inserire indirizzo: ");
                righe[3] = scan.nextLine();
                if (righe[3].isEmpty()) {
                    System.out.println(message);
                }
            }

            while (righe[4].isEmpty()) {
                System.out.print("Inserire ID del documento: ");
                righe[4] = scan.nextLine();
                if (righe[4].isEmpty()) {
                    System.out.println(message);
                }
            }

            try {
                Utente u = new Utente(index, righe[0], righe[1], date, righe[3], righe[4]);
                magazzino.utenti.add(index, u);
                System.out.println("Bene, acquisizione dei dati completata!");
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    },

    ESPORTAZIONE {
        @Override
        public void esegui() {
            System.out.println("Esportazione del file dei capi disponibili");
            //
            Magazzino magazzino = Service.getMagazzino();
            magazzino.esportaCapiDisponibili(magazzino.capi);
        }
    };

    public abstract void esegui();
}
