
public class Romboidale extends Piastrella{
    private int d1;
    private int d2;

    public Romboidale (int d1, int d2, int costo){
        super(costo);
        this.d1 = d1;
        this.d2 = d2;
    }


    //REQUIERS: d1 > 0 && d2 > 0
    //MODIFIES: nothing
    //EFFECTS: returns the area
    @Override
    public int getSuperficie() {
        return (d1 * d2) / 2;
    }

}
