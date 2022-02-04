package deque;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int nextLast;
    private int nextFirst;
    private int initSize = 4;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        int mid = items.length/2;
        nextFirst = mid - 1;
        nextLast = mid;
    }


    public void resize(int capacity) {

        T[] newItems = (T[]) new Object[capacity];

        int startPos = newItems.length / 2 - size / 2; /* draw a picture */
        int oldIndex = nextFirst + 1; /* Use MOD operation instead */
        int newIndex = startPos; /* newIndex for newItems */
        int count = 0;
        while (count < size) { /* yes! the original size! */
            newItems[newIndex] = items[oldIndex % items.length]; /* 3 + 1 = 4, 4 % 4 = 0 */
            oldIndex++; newIndex++;
            count++;
        }
        items = newItems;
        // size = capacity; bug! we don't need to change size!
        nextFirst = startPos - 1;
        nextLast = newIndex; /* or nextFirst + size */
    }

    private void resizeDown() {
        resize(size * 2 < initSize ? initSize : size * 2); /* if it is too small, make it 8 */
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) { /** FULL! */
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = ((nextFirst - 1) + items.length) % items.length;
        /* since in java, -5 % 6 == -5, we need to add items.length */
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length; /* nextLast += 1 */
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        /** Resize down */
        if ((double) size / items.length < 0.25 && items.length > initSize) {
            resizeDown();
        }

        nextFirst = (nextFirst + 1) % items.length;
        T removedItem = items[nextFirst];
        items[nextFirst] = null; /* since it is a reference */
        size--;
        return removedItem;
    }

    @Override
    public T removeLast() {
        //  0  1  2  3
        // [ ][x][x][x]
        // [ ][ ][ ][ ]
        if (isEmpty()) {
            return null;
        }

        /** Resize down */
        if ((double) size / items.length < 0.25 && items.length > initSize) {
            resizeDown();
        }

        nextLast = ((nextLast - 1) + items.length) % items.length;
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return removedItem;
    }

    @Override
    public T get(int index) {
        /* Test at the first place. It makes the condition later more succinct */
        if (index < 0 || index >= size) {
            return null;
        }

        int oldIndex = nextFirst + 1; /* will be MODed */
        while (index > 0) {
            oldIndex++;
            index--;
        }
        return items[oldIndex % items.length];
    }

    @Override
    /** Returns the number of items in the list. */
    public int size() {

        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public void printDeque(){

    }

}
