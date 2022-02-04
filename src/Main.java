import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int numOfThreads = Integer.parseInt(args[0]);
        int readerThreads = Integer.parseInt(args[1]);
        int nrFiles = Integer.parseInt(args[2]);

        LinkedList list = new LinkedList();
        MyBlockingQeue queue = new MyBlockingQeue(numOfThreads);
        MyBlockingQeue insertQeue = new MyBlockingQeue(numOfThreads);

        ArrayList<WorkerThread> threads = new ArrayList<>();
        for(int i = 0; i < numOfThreads - readerThreads; i++){
            threads.add(new WorkerThread(list, queue, insertQeue));
        }

        for(WorkerThread th: threads){
            th.start();
        }

        int size = nrFiles/readerThreads;
        int rest = nrFiles%readerThreads;
        int start = 0, end;

        ArrayList<ReaderThread> readers = new ArrayList<>();
        for(int i=0; i<readerThreads; i++){
            end = start + size;
            if(rest > 0){
                end = end + 1;
                rest = rest - 1;
            }
            ReaderThread reader = new ReaderThread(queue, start + 1, end + 1);
            readers.add(reader);
            start = end;
        }


        Long startTime = System.currentTimeMillis();

        for(ReaderThread reader: readers){
            reader.start();
        }

        for(ReaderThread reader: readers){
            reader.join();
        }

        while (!queue.isEmpty());

        Long endTime = System.currentTimeMillis();
        System.out.println("Duration time: "+ (double)(endTime - startTime));//ms

        for (WorkerThread t: threads){
            t.setDone(true);
            t.stopThread();
        }

        try {
            for(WorkerThread thread: threads){
                thread.join();
            }
        }catch (InterruptedException ex){
        }

        list.writeInFile();

    }
}
