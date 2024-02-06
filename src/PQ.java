
public class PQ {   // this claas is from project 2
    private int size;
    private int DEFAULT_CAPACITY = 4;
    private LargeDepositor[] heap;
    private int AUTOGROW_SIZE = 2 * DEFAULT_CAPACITY;


    public PQ(){
        this.heap= new LargeDepositor[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }

    public int size(){
        return size;
    }


    public void insert(LargeDepositor x){
        heap[++size] = x;
        swim(size);
    }


    public void swim(int i){
        if (i == 1)
            return;
        int parent = i / 2;
        while (i != 1 && heap[i].compareTo(heap[parent]) < 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }

    }


    public void swap(int i , int j){
        LargeDepositor temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    public void sink(int i){
        int left = 2 * i;
        int right = left + 1;

        if (left > size)
            return;

        while (left <= size) {
            int min = left;
            if (right <= size) {
                if (heap[left].compareTo(heap[right]) > 0)
                    min = right;
            }

            if (heap[i].compareTo(heap[min]) <= 0)
                return;
            else {
                swap(i, min);
                i = min;
                left = i * 2;
                right = left + 1;
            }
        }
    }



    public LargeDepositor getmax() {

        if (size == 0)
            return null;
        LargeDepositor root = heap[size];       // returns the max depositor
        size--;
        sink(size);

        return root;
    }




}

