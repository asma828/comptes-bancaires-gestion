package Model;

public class Versement extends Operation {
    private String source;

    public Versement(String numero, String date, double montant, String source) {
        super(numero, date, montant);
        this.source = source;
    }

    public String getSource() { return source; }
}
