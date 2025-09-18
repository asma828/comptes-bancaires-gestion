package Model;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(String code ,double tauxInteret){
        super(code);
        this.code=code;
        this.tauxInteret=tauxInteret;
    }
}
