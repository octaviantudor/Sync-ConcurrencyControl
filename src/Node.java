import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
    private volatile Node next;
    private Integer coef;
    private Integer exp;
    private final Lock lock = new ReentrantLock();

    public Node(Integer exp, Integer coef){
        this.exp = exp;
        this.coef = coef;
        this.next = null;
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", coef=" + coef +
                ", exp=" + exp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(next, node.next) &&
                Objects.equals(coef, node.coef) &&
                Objects.equals(exp, node.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, coef, exp);
    }
}
