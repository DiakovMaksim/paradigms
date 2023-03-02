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

Pred: true
Post: immutable(n) && R = a
toArray
 */
public class ArrayQueueADT {
    private Object[] elements = new Object[2];
    private int head = 0;
    private int tail = 1;
    private int size = 0;

//    Pred: element != null && queue != null
//    Post: n' = n + 1 && a[n'] == element && immutable(n)
//    enqueue(element)
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
        queue.size++;
    }

    private static void ensureCapacity(final ArrayQueueADT queue) {
        if (queue.size + 1 >= queue.elements.length) {
            Object[] copy = new Object[queue.elements.length * 2];
            int position = 1;
            for (int i = (queue.head + 1) % queue.elements.length; i != queue.tail; i = (i + 1) % queue.elements.length) {
                copy[position] = queue.elements[i];
                position++;
            }
            queue.head = 0;
            queue.tail = position;
            queue.elements = copy;
        }
    }
//    Pred: n >= 1 && queue != null
//    Post: immutable(n) && R = a[1] && n' == n
//    element
    public static Object element(final ArrayQueueADT queue) {
        assert queue.size != 0;
        return queue.elements[(queue.head + 1) % queue.elements.length];
    }
//    Pred: n >= 1 && queue != null
//    Post: n' == n - 1 && for i=1..n': a'[i] == a[i + 1] && R = a[1]
//    dequeue
    public static Object dequeue(final ArrayQueueADT queue) {
        assert queue.size != 0;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return queue.elements[queue.head];
    }
//    Pred: true && queue != null
//    Post: immutable(n) && R = n && n' == n
//    size
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }
//    Pred: true && queue != null
//    Post: immutable(n) && R = (n == 0) && n' == n
//    isEmpty
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }
//    Pred: true && queue != null
//    Post: n' = 0
//    clear
    public static void clear(final ArrayQueueADT queue) {
        queue.head = 0;
        queue.tail = 1;
        queue.size = 0;
    }
//    Pred: element != null && queue != null
//    Post: n' == n + 1 && for i=2..n': a'[i] = a[i - 1] && a'[1] == element
//    push(element)
    public static void push(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue);
        queue.elements[queue.head] = element;
        queue.head = (queue.head - 1 + queue.elements.length) % queue.elements.length;
        queue.size++;
    }
//    Pred: n >= 1 && queue != null
//    Post: immutable(n) && R = a[n] && n' == n
//    peek
    public static Object peek(final ArrayQueueADT queue) {
        assert queue.size != 0;
        return queue.elements[(queue.tail - 1 + queue.elements.length) % queue.elements.length];
    }
//    Pred: n >= 1 && queue != null
//    Post: n' == n - 1 && immutable(n') && R = a[n]
//    remove
    public static Object remove(final ArrayQueueADT queue) {
        assert queue.size != 0;
        queue.size--;
        queue.tail = (queue.tail - 1 + queue.elements.length) % queue.elements.length;
        return queue.elements[queue.tail];
    }
//    Pred: queue != null
//    Post: immutable(n) && R = a
//    toArray
    public static Object[] toArray(final ArrayQueueADT queue) {
        Object[] copy = new Object[queue.size];
        int position = 0;
        for (int i = (queue.head + 1) % queue.elements.length; i != queue.tail; i = (i + 1) % queue.elements.length) {
            copy[position] = queue.elements[i];
            position++;
        }
        return copy;
    }
}
