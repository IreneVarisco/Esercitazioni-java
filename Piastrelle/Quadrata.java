
public class Quadrata extends Piastrella{
    private int lato;
    
    
    public Quadrata(int lato, int costo){
        super(costo);
        this.lato = lato;
    }

    //REQUIERS: lato > 0
    //MODIFIES: nothing
    //EFFECTS: returns the area 
    @Override
    public int getSuperficie() {
        return lato * lato;
    }
    
}
