package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;

    public Compte(String code) {
        this.code = code;
        this.solde = 0;
        this.listeOperations = new ArrayList<>();
    }

    public void verser(double montant, String source) {
        solde += montant;
        listeOperations.add(new Versement(UUID.randomUUID().toString(),
                LocalDate.now().toString(), montant, source));
    }

    // Méthodes abstraites
    public abstract boolean retirer(double montant, String destination);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    // Getters
    public String getCode() { return code; }
    public double getSolde() { return solde; }
    public List<Operation> getListeOperations() { return listeOperations; }
}