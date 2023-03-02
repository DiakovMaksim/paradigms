package queue;
import static queue.ArrayQueueADT.*;
public class ArrayQueueADTTest {
    public static void main(String[] args) {
        ArrayQueueADT queue1 = new ArrayQueueADT();
        ArrayQueueADT queue2 = new ArrayQueueADT();
        for (int i = 0; i < 5; i++) {
            enqueue(queue1,"q1_" + i);
            enqueue(queue2,"q2_" + i);
        }
        dumpQueue(queue1);
        dumpQueue(queue2);
    }
    private static void dumpQueue(ArrayQueueADT queue) {
        while (!isEmpty(queue)) {
            final Object value = dequeue(queue);
            System.out.println(size(queue) + " " + value);
        }
    }
}
