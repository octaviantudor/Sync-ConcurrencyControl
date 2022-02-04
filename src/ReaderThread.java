import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ReaderThread extends Thread {

    private MyBlockingQeue queue;
    private int start;
    private int end;

    public ReaderThread(MyBlockingQeue queue, int start, int end){
        this.queue = queue;
        this.start = start;
        this.end = end;
    }


    @Override
    public void run(){
        for(int i = start; i<end; i++) {
            String filename = "f" + i + ".txt";
            System.out.println(this.getName() + " reading from file " + filename);
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] args = line.split(" ");
                    Integer coef = Integer.parseInt(args[0]);
                    Integer exp = Integer.parseInt(args[1]);
                    Node node = new Node(exp, coef);

                    queue.push(node);
                    //System.out.println(node);

                    line = reader.readLine();
                }
            } catch (IOException | InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
