public class CompteCourant extends Compte{
private float Découvert;

public CompteCourant(String code , float Découvert){
    super(code);
    this.code=code;
    this.Découvert=Découvert;
}
}
