import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQeue {

    private final Queue<Node> queue = new LinkedList<>();
    private final int cap;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MyBlockingQeue(int cap) {
        this.cap = cap;
    }

    public void push(Node node) throws InterruptedException {
        lock.lock();
        try {
            queue.add(node);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Node pop() throws InterruptedException {
        Node node;
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            node = queue.poll();
            notFull.signal();

        } finally {
            lock.unlock();
        }
        return node;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
