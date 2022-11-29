import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range {
    private final int from, to, step;
    //EFFECTS: Costruisce un elemento di tipo Range e solleva un'eccezione di tipo
    //         IllegalArgumentException se step=0
    public Range(int from, int to, int step){
        if (step == 0) throw new IllegalArgumentException("step dev'essere diverso da 0");
        this.from = from;
        this.to = to;
        this.step = step;
    }
    public Range(int to, int step){
        this(0, to, step);

    }
    public Range(int to){
        this(to,to>0 ? +1 : -1);
    }

    public Iterator<Integer> iterator(){
        return new Iterator<Integer>(){
            int next = from;
            boolean hasNext = (step > 0 ? from < to : from > to);
            @Override
            public boolean hasNext() {
                if (!hasNext){
                    next += step;
                    hasNext = from < to ? next < to : next > to;//verifico se next ha già raggiunto o meno to    
                }
                return hasNext; 
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                hasNext = false;
                return next;
            }

        };
    }
}
