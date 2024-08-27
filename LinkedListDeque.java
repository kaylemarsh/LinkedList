import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;
        public Node(T i, Node<T> n, Node<T> p) {
            item = i;
            next = n;
            prev = p;
        }
    }
    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    @Override
    /* This method will add a Node to the front of the list. */
    public void addFirst(T x) {
        Node<T> first = new Node(x, sentinel.next, sentinel);
        sentinel.next = first;
        sentinel.next.next.prev = first;
        size++;
    }

    @Override
    /* This method will add a Node to the back of the list. */
    public void addLast(T x) {
        Node<T> last = new Node(x, sentinel, sentinel.prev);
        sentinel.prev = last;
        sentinel.prev.prev.next = last;
        size++;
    }

    @Override
    /* This method will convert the Node list into an Array list. */
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> addItem = sentinel.next;
        for (int i = 0; i < size; i++) {
            returnList.add(addItem.item);
            addItem = addItem.next;
        }
        return returnList;
    }

    @Override
    /* This method will return a boolean indicating if the list is empty or not. */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    /* This method will return the size of the list. */
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnValue = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return returnValue;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnValue = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return returnValue;
    }

    @Override
    /* This method will return the value at the given index iteratively. */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            Node<T> iterativeList = sentinel.next;
            for (int x = 0; x < index; x++) {
                iterativeList = iterativeList.next;
            }
            return iterativeList.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            return helper(index, sentinel.next);
        }
    }

    /* Helper function that returns the item at a given index for .get recursively. */
    public T helper(int index, Node<T> n) {
        if (index == 0) {
            return n.item;
        } else {
            return helper(index - 1, n.next);
        }
    }
}
