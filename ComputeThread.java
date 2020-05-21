import java.lang.Thread;
import java.lang.Runnable;

public class ComputeThread extends AbstractThread implements Runnable {
    
    public Thread thread = null;

    public ComputeThread( int lower, int upper, int mc) {

        super( lower, upper, mc);
    }

    public ComputeThread( int lower, int upper, String mc) {

        super( lower, upper, mc);
    }

    public void init() {

        started = true;
        thread = new Thread( this);
        thread.start();
    }

    public void start() {
    
        run();
    }

    public void close() {

        
    }

    public void run() {

        //System.out.println("I get done");
        endSum = summation();
        //System.out.println("i get finished");
        isDone = true;
    }

    public void join() {

        //thread.join();
    }
}