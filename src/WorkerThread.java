public class WorkerThread extends Thread {

    private final LinkedList list;
    private final MyBlockingQeue queue;
    private final MyBlockingQeue insertQeue;
    private boolean isBusy;
    private boolean isDone;

    public WorkerThread(LinkedList list, MyBlockingQeue queue, MyBlockingQeue insertQeue) {
        this.list = list;
        this.queue = queue;
        this.insertQeue = insertQeue;
        this.isBusy = false;
        this.isDone = false;
    }


    public synchronized void setDone(boolean done) {
        isDone = done;
    }

    public void stopThread() {
        isDone = true;
        this.interrupt();
    }


    @Override
    public void run() {
        while (!isDone) {
            try {
                Node cr = queue.pop();
                System.out.println(this.getName() + " working on " + cr);
                if (cr != null) {
                    System.out.println(this.getName() + " working on list with " + cr);
                    Node exists = list.exists(cr);
                    if (exists == null) {
                        list.insert(cr);
                    } else {
                        list.update(exists, cr);
                    }
                    System.out.println(this.getName() + " Done working...");
                } else {
                    System.out.println("E NULL!!!");
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}
