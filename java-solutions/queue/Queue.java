package queue;

public interface Queue {
    //    Pred: element != null
    //    Post: n' = n + 1 && a[n'] == element && immutable(n)
    //    enqueue(element)
    void enqueue(Object element);

    //    Pred: n >= 1
    //    Post: immutable(n) && R = a[1] && n' == n
    //    element
    Object element();

    //    Pred: n >= 1
    //    Post: n' == n - 1 && for i=1..n': a'[i] == a[i + 1] && R = a[1]
    //    dequeue
    Object dequeue();

    //    Pred: true
    //    Post: immutable(n) && R = n && n' == n
    //    size
    int size();

    //    Pred: true
    //    Post: immutable(n) && R = (n == 0) && n' == n
    //    isEmpty
    boolean isEmpty();

    //    Pred: true
    //    Post: n' = 0
    //    clear
    void clear();

    //    Pred: element != null
    //    Post: n' == n + 1 && for i=2..n': a'[i] = a[i - 1] && a'[1] == element
    //    push(element)
    void push(Object element);

    //    Pred: n >= 1
    //    Post: immutable(n) && R = a[n] && n' == n
    //    peek
    Object peek();

    //    Pred: n >= 1
    //    Post: n' == n - 1 && immutable(n') && R = a[n]
    //    remove
    Object remove();

    //    Pred: true
    //    Post: n' == n && immutable(n) && R = a
    //    toArray
    Object[] toArray();

    //    Pred: true
    //    Post: n' == n && immutable(n) && R = a.toString()
    //    toStr
    String toStr();
    //    Pred: true
    //    Post: n' == n && immutable(n) && R = a.count(element)
    //    count
    int count(Object element);
}
