package Model;

import java.time.LocalDate;
import java.util.UUID;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(String code, double tauxInteret) {
        super(code);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public boolean retirer(double montant, String destination) {
        if (solde >= montant) {
            solde -= montant;
            listeOperations.add(new Retrait(UUID.randomUUID().toString(),
                    LocalDate.now().toString(), montant, destination));
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        return solde * tauxInteret;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Epargne: " + code);
        System.out.println("Solde: " + solde);
        System.out.println("Taux: " + tauxInteret);
    }
}