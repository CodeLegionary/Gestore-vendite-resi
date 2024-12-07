package org.javabasics.service;

import org.javabasics.model.Magazzino;
import org.javabasics.model.Operazione;


public class Service {
    //tramite fra Controller e Model
    //una sorta di seconda interfaccia
    //che tiene all'oscuro Model da Controller e viceversa

    private static Service service;
    Magazzino magazzino;



    private Service() {
        final String itemsFile = "assets/capi.csv";
        final String usersFile = "assets/utenti.csv";
        final String salesFile = "assets/vendite.csv";
        magazzino = new Magazzino();

        magazzino.loadFromCSV(itemsFile);
        magazzino.loadFromCSV(usersFile);
        magazzino.loadFromCSV(salesFile);

    }

    public static Service getInstance() {
        if (service == null)
            service = new Service();

        return service;
    }

    public static void resetInstance() {
        service = null;
    }

    public static Magazzino getMagazzino() {
        return getInstance().magazzino;
    }

    public void selezionaOpzione(Integer scelta) {
        try {
            Operazione[] operazioni = Operazione.values();

            if (scelta >= 1 && scelta <= operazioni.length)
                operazioni[scelta - 1].esegui();

        } catch (Exception e) {
            System.out.println("Si è verificato un errore: " + e.getMessage());
        }
    }
}
//OTHERWISE BIll Pugh Singleton design

/*
public class Service {
    private Service() {}

    private static class ServiceHolder {
        private static final Service INSTANCE = new Service();
    }

    public static Service getInstance() {
        return ServiceHolder.INSTANCE;
    }
}
*/

/*
ALTERNATIVELY BOOK CROSSING EXAMPLE

import java.util.concurrent.ConcurrentHashMap;

public class BookService {
    private static class ServiceHolder {
        private static final BookService INSTANCE = new BookService();
    }

    private Map<String, Boolean> bookInventory = new ConcurrentHashMap<>();

    private BookService() {
        // Initialize book inventory
        bookInventory.put("Book1", true); // true means available
        bookInventory.put("Book2", true);
    }

    public static BookService getInstance() {
        return ServiceHolder.INSTANCE;
    }

    public boolean book(String bookName) {
        return bookInventory.computeIfPresent(bookName, (key, value) -> value ? false : value) != null;
    }
}

//following are security measures

@Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException
        ("Cloning of this class is not allowed.");



////////////////////////////////////


 */
