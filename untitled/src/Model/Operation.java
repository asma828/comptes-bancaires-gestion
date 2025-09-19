package Model;

public abstract class Operation {
    protected String numero;
    protected String date;
    protected double montant;

    public Operation(String numero, String date, double montant) {
        this.numero = numero;
        this.date = date;
        this.montant = montant;
    }

    public String getNumero() { return numero; }
    public String getDate() { return date; }
    public double getMontant() { return montant; }
}