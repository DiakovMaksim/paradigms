package queue;

import java.util.Objects;

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
 */
public class ArrayQueue {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 1;
    private int size = 0;

//    Pred: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(n)
//    enqueue(element)
    public void enqueue(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
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
    public Object element() {
        assert size != 0;
        return elements[(head + 1) % elements.length];
    }
//    Pred: n >= 1
//    Post: n' == n - 1 && for i=1..n': a'[i] == a[i + 1] && R = a[1]
//    dequeue
    public Object dequeue() {
        assert size != 0;
        head = (head + 1) % elements.length;
        size--;
        return elements[head];
    }
//    Pred: true
//    Post: immutable(n) && R = n && n' == n
//    size
    public int size() {
        return size;
    }
//    Pred: true
//    Post: immutable(n) && R = (n == 0) && n' == n
//    isEmpty
    public boolean isEmpty() {
        return size == 0;
    }
//    Pred: true
//    Post: n' = 0
//    clear
    public void clear() {
        head = 0;
        tail = 1;
        size = 0;
    }
//    Pred: element != null
//    Post: n' == n + 1 && for i=2..n': a'[i] = a[i - 1] && a'[1] == element
//    push(element)
    public void push(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity();
        elements[head] = element;
        head = (head - 1 + elements.length) % elements.length;
        size++;
    }
//    Pred: n >= 1
//    Post: immutable(n) && R = a[n] && n' == n
//    peek
    public Object peek() {
        assert size != 0;
        size--;
        return elements[(tail + 1) % elements.length];
    }
//    Pred: n >= 1
//    Post: n' === n - 1 && immutable(n') && R = a[n]
//    remove
    public Object remove() {
        assert size != 0;
        size--;
        tail = (tail - 1 + elements.length) % elements.length;
        return elements[tail];
    }
}
