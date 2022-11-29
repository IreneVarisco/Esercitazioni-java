import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T>{
    private final Iterator<T> source;
    private final Predicate<T> filter;

    private T next = null;
    private boolean hasNext = false;

    public FilterIterator(final Iterator<T> source, Predicate<T> filter){
        this.source = source;
        this.filter = filter;
    }

    //hasNext() dell'iteratore source sia true
    //next() soddisfi il predicato filter
    @Override
    public boolean hasNext() {
        while (!hasNext && source.hasNext()){
            next= source.next();
            hasNext=filter.test(next);
        }
        return hasNext;
    }

    //restituire l'elemento se hasNext()
    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        hasNext = false;
        return next;
    }
}
