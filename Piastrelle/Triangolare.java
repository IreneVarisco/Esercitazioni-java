
public class Triangolare extends Piastrella{
    private int base;
    private int altezza;

    public Triangolare (int base, int altezza, int costo){
        super(costo);
        this.base = base;
        this.altezza = altezza;
    }

   
    //REQUIERS: base > 0 && altezza > 0
    //MODIFIES: nothing
    //EFFECTS: returns the area
    @Override
    public int getSuperficie() {
        return (base * altezza)/2;
    }

}
