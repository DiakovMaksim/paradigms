package queue;

import java.util.Arrays;
import java.util.Objects;

public class AbstractQueue implements Queue{
    int size = 0;
    @Override
    public void enqueue(Object element) {
        Objects.requireNonNull(element);
        size++;
        enqueueImpl(element);
    }

    protected void enqueueImpl(Object element) {
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object dequeue() {
        assert size != 0;
        size--;
        return dequeueImpl();
    }

    protected Object dequeueImpl() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(Object element) {
        Objects.requireNonNull(element);
        size++;
        pushImpl(element);
    }

    protected void pushImpl(Object element) {
    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public Object remove() {
        assert size != 0;
        size--;
        return removeImpl();
    }

    protected Object removeImpl() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size];
        int position = 0;
        for (int i = 0; i < size; i++) {
            copy[position++] = dequeue();
        }
        for (Object back : copy) {
            enqueue(back);
        }
        return copy;
    }

    @Override
    public String toStr() {
        return Arrays.toString(toArray());
    }
    @Override
    public int count(Object element) {
        Object[] copy = new Object[size];
        int position = 0;
        int result = 0;
        for (int i = 0; i < size; i++) {
            copy[position++] = dequeue();
        }
        for (Object back : copy) {
            if (back.equals(element)) {
                result++;
            }
            enqueue(back);
        }
        return result;
    }
}
