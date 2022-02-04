package deque;

public class AList {
    private int size;
    private int[] aList;
    /** Creates an empty list. */
    public AList() {
        aList = new int[8];
        size = 0;
    }



    /** Inserts X into the back of the list. */
    public void addLast(int x) {

            aList[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        if(size > 0)
            return aList[size-1];
        else
            return aList[size];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {

        return aList[i];
    }

    /** Returns the number of items in the list. */
    public int size() {

        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {

        size--;
        return 0;
    }
}
