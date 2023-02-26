import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Pavimentazione implements Rivestimento, Iterable<Pavimentazione.Elemento> {

    public static class Elemento implements Rivestimento {
        public final Rivestimento rivestimento;
        public final int quantita;

        public Elemento(final int quantita, final Rivestimento rivestimento) {
            this.quantita = quantita;
            this.rivestimento = rivestimento;
        }

        //EFFECTS: returns the price of an element
        @Override
        public int getCosto() {
            return rivestimento.getCosto() * quantita;
        }

        //EFFECTS: returns the area of an element
        @Override
        public int getSuperficie() {
            return rivestimento.getSuperficie() * quantita;
        }

    }

    private final Collection<Elemento> elementi;

    public Pavimentazione(final Collection<Elemento> elementi) {
        this.elementi = List.copyOf(elementi);
        if (elementi.isEmpty()) {
            throw new IllegalArgumentException("Ci deve essere sempre almeno una componente.");
        }

    }

    //REQUIERS:
    //MODIFIES: 
    //EFFECTS: returns the price of pavimentazione
    @Override
    public int getCosto() {
        int costo = 0;
        for (final Rivestimento r : elementi) {
            costo += r.getCosto();
        }
        return costo;
    }

    //REQUIERS:
    //MODIFIES: 
    //EFFECTS: returns the area of pavimentazione
    @Override
    public int getSuperficie() {
        int superficie = 0;
        for (final Rivestimento r : elementi) {
            superficie += r.getSuperficie();
        }
        return superficie;
    }

    @Override
    public Iterator<Pavimentazione.Elemento> iterator() {
        return elementi.iterator();
    }
}
