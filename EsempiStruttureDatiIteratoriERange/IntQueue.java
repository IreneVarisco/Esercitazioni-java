import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class IntQueue {
    // CAMPI
    /*- La struttura dati contenente gli elementi dell'IntQueue this. */
    private final int[] elements;
  
    /*- Gli indici della testa e della coda della IntQueue.
     *  Nello specifico, head è l'indice del primo elemento di this (-1 se la coda è vuota),
     *  mentre tail indica l'indice della prima posizione disponibile di this (head = tail se la coda è piena).
     */
    private int head, tail;

    private boolean modified;
  
    /*-
     * ABS FUN:  AF(elements, head, tail)
     *           = [ elements[i] | head <= i < tail ]
     *           = [elements[head], elements[head+1], ..., elements[tail-1]] se -1 < head <= tail
     *           o [elements[head], elements[head+1], ..., elements[elements.size-1], elements[0], ..., elements[tail-1]] se head > tail
     *
     * REP INV:  la coda non contiene più elementi della sua capienza massima,
     *          -1 <= head < size
     *           0 <= tail < size
     *           head == -1 ⇒ tail = 0
     */
  
    // COSTRUTTORI
    /*-
     * EFFECTS: Inizializza this affinché rappresenti una coda vuota con dimensione massima n.
     *          Solleva un'eccezione di tipo IllegalArgumentException se n <= 0.
     */
    public IntQueue(int n) {
          if (n <= 0) throw new IllegalArgumentException("Impossibile creare coda. Richiesta dimensione positiva.");
  
      elements = new int[n];
      head = -1;
      tail = 0;
  
      assert repOK();
    }
  
    // Metodi
    /*-
     * MODIFIES: this
     * EFFECTS: Aggiunge l'elemento x alla coda this se la coda non è piena,
     *          altrimenti solleva un'eccezione di tipo FullQueueException
     *          this_post = this + [x]
     */
    public void enqueue(int x) {
      if (isFull()) throw new FullQueueException("Impossibile aggiungere elemento. Coda piena.");
      if (isEmpty()) head = 0;
      elements[tail] = x;
      tail = (tail + 1) % elements.length;
  
      assert repOK();
      modified=true;
    }
  
    /*-
     * MODIFIES: this
     * EFFECTS: Rimuove e restituisce l'elemento in testa alla coda this, se presente.
     *          Solleva un'eccezione di tipo EmptyQueueException se la coda è vuota
     *          this = [x1, x2, ..., x_k], k < n, this_post = [x2, ..., x_k]
     */
    public int dequeue() {
      if (isEmpty()) throw new EmptyQueueException("Impossibile estrarre elemento. Coda vuota.");
      int r = elements[head];
      head = (head + 1) % elements.length;
      if (head == tail) {
        head = -1;
        tail = 0;
      }
  
      assert repOK();
      modified=true;
      return r;
    }
  
    /*-
     * EFFECTS: restituisce true se la coda this è piena, false altrimenti.
     */
    public boolean isFull() {
      return head == tail;
    }
  
    /*-
     * EFFECTS: restituisce true se la coda this è vuota, false altrimenti.
     */
    public boolean isEmpty() {
      return head == -1;
    }
  
    /*-
     * EFFECTS: restituisce il numero di elementi contenuti in this
     */
    public int size() {
      if (isEmpty()) return 0;
      if (isFull()) return elements.length;
      return (tail - head + elements.length) % elements.length;
    }
  
    private boolean repOK() {
      return size() <= elements.length
          && elements != null
          && head >= -1
          && head < elements.length
          && tail >= 0
          && tail < elements.length
          && (head != -1 || tail == 0);
    }
  
    @Override
    public String toString() {
      assert repOK();
  
      StringBuilder sb = new StringBuilder("IntQueue : [");
      if (!isEmpty()) {
        int i;
        for (i = 0; i < size() - 1; i++)
          sb.append(elements[(head + i) % elements.length]).append(", ");
        sb.append(elements[(head + i) % elements.length]);
      }
      sb.append(']');
      return sb.toString();
    }
  
    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof IntQueue)) return false;
      IntQueue other = (IntQueue) obj;
  
      if (size() != other.size() || elements.length != other.elements.length) return false;
      for (int i = 0; i < size(); i++)
        if (elements[(head + i) % elements.length]
            != other.elements[(other.head + i) % elements.length]) return false;
  
      return true;
    }
  
    @Override
    public int hashCode(){
      int result = Integer.hashCode(elements.length);
      for (int i = 0; i < size(); i++)
        result = 31 * result + Integer.hashCode(elements[(head + i) % elements.length]);
  
      return result;
    }


    public Iterator<Integer> iterator(){
      modified=false;
      return new Iterator<Integer>(){
        int indexNext = head;
        boolean hasNext = isFull();
        @Override
        public boolean hasNext() {
          if (!hasNext){
            hasNext = indexNext!=tail && indexNext != -1; 
          }
          return hasNext; 
          //c'é ancora un elemento da leggere se l'indice é diverso dalla coda dell'array 
          //e se é diverso da -1 (il valore che ha head quando l'array é vuoto)
        }

        @Override
        public Integer next() {
          if (!hasNext()) throw new NoSuchElementException();
          if (modified) throw new ConcurrentModificationException();
          int res = elements[indexNext];
          indexNext = (indexNext+1) % elements.length;
          hasNext = false;
          return res;
        }
      
      };
      
    } 
  }