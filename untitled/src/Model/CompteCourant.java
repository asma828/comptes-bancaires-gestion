package Model;

public class CompteCourant extends Compte {
    private float decouvert;

    public CompteCourant(String code, float decouvert) {
        super(code);
        this.code = code;
        this.decouvert = decouvert;
    }
}
