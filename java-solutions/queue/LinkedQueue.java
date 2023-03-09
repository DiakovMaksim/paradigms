package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;
    private void initialize() {
        head = new Node(null, null, null);
        tail = new Node(null, null, head);
        head.prev = tail;
        size = 0;
    }
    public LinkedQueue() {
        initialize();
    }
//    Pred: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(n)
//    enqueue(element)
    @Override
    protected void enqueueImpl(final Object element) {
        tail.element = element;
        tail = new Node(null, null, tail);
        tail.next.prev = tail;
    }
//    Pred: n >= 1
//    Post: immutable(n) && R = a[1] && n' == n
//    element
    @Override
    public Object element() {
        assert size != 0;
        return head.prev.element;
    }
//    Pred: n >= 1
//    Post: n' == n - 1 && for i=1..n': a'[i] == a[i + 1] && R = a[1]
//    dequeue
    @Override
    public Object dequeueImpl() {
        Object result = head.prev.element;
        head = new Node(null, head.prev.prev, null);
        return result;
    }
//    Pred: true
//    Post: n' = 0
//    clear
    @Override
    public void clear() {
        initialize();
    }
//    Pred: element != null
//    Post: n' == n + 1 && for i=2..n': a'[i] = a[i - 1] && a'[1] == element
//    push(element)
    @Override
    public void pushImpl(final Object element) {
        head.element = element;
        head = new Node(null, head, null);
        head.prev.next = head;
    }
//    Pred: n >= 1
//    Post: immutable(n) && R = a[n] && n' == n
//    peek
    @Override
    public Object peek() {
        assert size != 0;
        return tail.next.element;
    }
//    Pred: n >= 1
//    Post: n' == n - 1 && immutable(n') && R = a[n]
//    remove
    @Override
    public Object remove() {
        Object result = tail.next.element;
        tail = new Node(null, null, tail.next.next);
        return result;
    }
    @Override
    public int count(Object element) {
        Node temp = head.prev;
        int result = 0;
        while (temp.element != null) {
            if (temp.element.equals(element)) {
                result++;
            }
            temp = temp.prev;
        }
        return result;
    }
    private static class Node {
        private Object element;
        private Node prev;
        private Node next;
        private Node(Object element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
