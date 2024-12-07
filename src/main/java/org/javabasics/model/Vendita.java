package org.javabasics.model;

public class Vendita extends Model {

    private Integer idCapo;
    private Integer idUtente;

    public Vendita() {}

    public Vendita(Integer id, Integer capo, Integer utente) {
        super.id= id;
        idCapo= capo;
        idUtente= utente;
    }

    public Integer getIdCapo() {
        return idCapo;
    }
}
