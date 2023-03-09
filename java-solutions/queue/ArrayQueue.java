package queue;

/*
Model: a[1]..a[n]
Invariant: n >= 0 && for i=1..n:a[i] != null

Let immutable(n): for i=1..n: a'[i] == a[i]

Pred: element != null
Post: n' = n + 1 && a[n'] == element && immutable(n)
enqueue(element)

Pred: n >= 1
Post: immutable(n) && R = a[1] && n' == n
element

Pred: n >= 1
Post: n' == n - 1 && for i=1..n': a'[i] == a[i + 1] && R = a[1]
dequeue

Pred: true
Post: immutable(n) && R = n && n' == n
size

Pred: true
Post: immutable(n) && R = (n == 0) && n' == n
isEmpty

Pred: true
Post: n' = 0
clear

Pred: element != null
Post: n' == n + 1 && for i=2..n': a'[i] = a[i - 1] && a'[1] == element
push(element)

Pred: n >= 1
Post: immutable(n) && R = a[n] && n' == n
peek

Pred: n >= 1
Post: n' === n - 1 && immutable(n') && R = a[n]
remove

Pred: true
Post: immutable(n) && R = a
toArray

Pred: true
Post: n' == n && immutable(n) && R = a.toString()
toStr
 */
public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 1;

//    Pred: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(n)
//    enqueue(element)
    @Override
    protected void enqueueImpl(final Object element) {
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    private void ensureCapacity() {
        if (size + 1 >= elements.length) {
            Object[] copy = new Object[elements.length * 2];
            int position = 1;
            for (int i = (head + 1) % elements.length; i != tail; i = (i + 1) % elements.length) {
                copy[position] = elements[i];
                position++;
            }
            head = 0;
            tail = position;
            elements = copy;
        }
    }
//    Pred: n >= 1
//    Post: immutable(n) && R = a[1] && n' == n
//    element
    @Override
    public Object element() {
        assert size != 0;
        return elements[(head + 1) % elements.length];
    }
//    Pred: n >= 1
//    Post: n' == n - 1 && for i=1..n': a'[i] == a[i + 1] && R = a[1]
//    dequeue
    @Override
    public Object dequeueImpl() {
        head = (head + 1) % elements.length;
        return elements[head];
    }
//    Pred: true
//    Post: n' = 0
//    clear
    @Override
    public void clear() {
        head = 0;
        tail = 1;
        size = 0;
        elements = new Object[2];
    }
//    Pred: element != null
//    Post: n' == n + 1 && for i=2..n': a'[i] = a[i - 1] && a'[1] == element
//    push(element)
    @Override
    public void pushImpl(final Object element) {
        ensureCapacity();
        elements[head] = element;
        head = (head - 1 + elements.length) % elements.length;
    }
//    Pred: n >= 1
//    Post: immutable(n) && R = a[n] && n' == n
//    peek
    @Override
    public Object peek() {
        assert size != 0;
        return elements[(tail - 1 + elements.length) % elements.length];
    }
//    Pred: n >= 1
//    Post: n' == n - 1 && immutable(n') && R = a[n]
//    remove
    @Override
    public Object removeImpl() {
        tail = (tail - 1 + elements.length) % elements.length;
        return elements[tail];
    }
//    Pred: true
//    Post: n' == n && immutable(n) && R = a
//    toArray
    @Override
    public Object[] toArray() {
        Object[] copy = new Object[size];
        int position = 0;
        for (int i = (head + 1) % elements.length; i != tail; i = (i + 1) % elements.length) {
            copy[position] = elements[i];
            position++;
        }
        return copy;
    }
    @Override
    public int count(Object element) {
        int result = 0;
        for (int i = (head + 1) % elements.length; i != tail; i = (i + 1) % elements.length) {
            if (elements[i].equals(element)) {
                result++;
            }
        }
        return result;
    }
}
