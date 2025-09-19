package Model;

public class Retrait extends Operation {
    private String destination;

    public Retrait(String numero, String date, double montant, String destination) {
        super(numero, date, montant);
        this.destination = destination;
    }

    public String getDestination() { return destination; }
}