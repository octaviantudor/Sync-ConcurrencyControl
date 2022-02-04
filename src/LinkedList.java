import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedList {
    private Node start;
    private Integer count;
    //private Lock lock = new ReentrantLock();

    public LinkedList(){
        start = null;
        count = 0;
    }


    public Node insert(Node node){
        //lock.lock();
        Node out = null;
        Node cr = start;
        if(cr != null)
            cr.lock();
        Node prev = null;
        Node aux = null;
        while (cr != null && node.getExp() < cr.getExp()){
            aux = prev;
            prev = cr;
            cr = cr.getNext();
            if(cr != null) {
                cr.lock();
            }
            if(aux != null){
                aux.unlock();
            }
        }

        if(cr != null && cr.getExp().equals(node.getExp())){
            cr.unlock();
            if(prev != null)
                prev.unlock();
            update(cr, node);
            return null;
        }

        if(prev == null){
            start = node;
        }
        else {
            prev.setNext(node);
        }
        node.setNext(cr);
        count++;
        if(prev != null){
            prev.unlock();
        }
        if(cr != null){
            cr.unlock();
        }
        //lock.unlock();
        return out;
    }

    public Node exists(Node node){
        //lock.lock();
        Node cursor = start;
        if(cursor != null){
            cursor.lock();
        }
        while (cursor != null){
            //cursor.lock();
            if(cursor.getExp().equals(node.getExp())){
                cursor.unlock();
                return cursor;
            }
            Node prev = cursor;
            cursor = cursor.getNext();
            if(cursor!=null)
                cursor.lock();
            prev.unlock();
        }
        //lock.unlock();
        return null;
    }

    public void updateSingle(Node cr, Node node){
        cr.setCoef(cr.getCoef() + node.getCoef());
    }

    public Node update(Node cursor, Node node){
        //lock.lock();
        //Node cursor = exists(node);
        cursor.lock();
        cursor.setCoef(cursor.getCoef() + node.getCoef());
        cursor.unlock();
        //lock.unlock();
        return cursor;
    }

    public void print(){
        Node cursor = start;
        while (cursor.getNext() != null){
            System.out.println(cursor.toString());
            if(cursor.getNext() != null)
                cursor = cursor.getNext();
        }
        System.out.println(cursor);
    }

    public void writeInFile(){
        try(PrintWriter pw = new PrintWriter("out.txt")) {
            Node cursor = start;
            while (cursor.getNext() != null){
                pw.println(cursor.getCoef()+" "+cursor.getExp());
                if(cursor.getNext() != null)
                    cursor = cursor.getNext();
            }
            pw.println(cursor.getCoef()+" "+cursor.getExp());
        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
}
