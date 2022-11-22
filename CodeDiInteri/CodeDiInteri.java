package CodeDiInteri;


public class CodeDiInteri {
    // campi
    private int head, tail;
    private int[] elements;

    // costruttori
    // costruisce una coda di dimensione n, exception se n <= 0
    public CodeDiInteri(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        
        elements = new int[n];
        head = -1;
        tail = 0;
        assert repOk();
    }

    // aggiunge elemento n in coda a thisse la coda non è piena altrimenti
    // exception. this = [x1, x2, ..., xk], this_post = [x1, x2, ..., xk, n]
    public void enqueue(int n) {
        if (isFull()){
            throw new FullQueueException("impossibile inserire elemento. coda piena");

        }
        elements[tail] = n;
        tail = (tail + 1) % elements.length;

        
    }

    // restituisce l'elemento in testa alla coda e lo elimina da this se la coda non
    // è vuota, altrimenti exception
    public int dequeue(){
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        int result = elements[head];
        head = (head + 1) % elements.length;

        if (head == tail){
            head = -1;
            tail= 0;
        }

        assert repOk();
        return result;
    }

    // restituisce il numero di elementi nella coda
    public int size(){
        return (tail - head + elements.length) % elements.length;
    }

    // restituisce true se la coda è vuota o false altrimenti
    public boolean isEmpty(){
        return head == -1;
    }

    // restituisce true se la coda è piena o false altrimenti
    public boolean isFull(){
        return head == tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IntQueue: [");

        if (size() > 0) {
            int i;
            for (i = 0; i < size(); i++) {
                sb.append(elements[(head + 1) % elements.length] + ", ");
            }
            sb.append(elements[(head + i) % elements.length]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CodeDiInteri)) {
            return false;
        }
        CodeDiInteri other = (CodeDiInteri) o;
        if (elements.length != other.elements.length) {
            return false;
        }

        if (size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (elements[(head + 1) % elements.length] != other.elements[(other.head + 1) % other.elements.length]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(elements.length);

        for (int i = 0; i < size(); i++) {
            result = 31 * result + Integer.hashCode(elements[(head + i) % elements.length]);
        }
        return result;
    }

    private boolean repOk() {
        return (-1 <= head && head < elements.length)
                && (0 <= tail && tail < elements.length)
                && (head != 1 || tail == 0);
    }
}
