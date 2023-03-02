package queue;

public class ArrayQueueModuleTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue("E" + i);
            final Object value = ArrayQueueModule.peek();
            System.out.println(ArrayQueueModule.size() + " " + value);
            ArrayQueueModule.clear();
        }
        while (!ArrayQueueModule.isEmpty()) {
            final Object value = ArrayQueueModule.dequeue();
            System.out.println(ArrayQueueModule.size() + " " + value);
        }
    }
}
