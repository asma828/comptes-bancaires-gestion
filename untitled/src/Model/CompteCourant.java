package Model;

import java.time.LocalDate;
import java.util.UUID;

public class CompteCourant extends Compte {
    private double decouvert;

    public CompteCourant(String code, double decouvert) {
        super(code);
        this.decouvert = decouvert;
    }

    @Override
    public boolean retirer(double montant, String destination) {
        if (solde - montant >= -decouvert) {
            solde -= montant;
            listeOperations.add(new Retrait(UUID.randomUUID().toString(),
                    LocalDate.now().toString(), montant, destination));
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        return 0;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Courant: " + code);
        System.out.println("Solde: " + solde);
        System.out.println("Découvert: " + decouvert);
    }
}
