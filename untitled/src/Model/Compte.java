package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
    // ctrl alt l
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;

    public Compte(String code) {
        this.code = code;
        this.solde = 0;
        this.listeOperations = new ArrayList<>();
    }

    // liste des comptes static
    public void retirer(float montante) {
        solde = solde - montante;
    }

    public void calculerInteret(float taux, int dureé) {
        double interét = solde * taux * dureé;
    }

    void afficherDetails() {
    }

    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double x) {
        solde = x;
    }

    public String setCode(){
        return code = code;
    }
}
