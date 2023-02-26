public abstract class Piastrella implements Rivestimento{
    private final int costo;

    public Piastrella(int costo){
        this.costo = costo;
    }

    //REQUIERS:
    //MODIFIES: 
    //EFFECTS: returns the price of piastrella
    @Override
    public int getCosto(){
        return costo;
    }
    
}
