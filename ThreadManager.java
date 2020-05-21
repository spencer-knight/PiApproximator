import java.math.BigDecimal;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class ThreadManager {
    
    AbstractThread[] threads = null;
    int lower = -1;
    int upper = -1;
    String sMc = null;
    int iMc = -1;
    int incr = -1;
    int numThreads = -1;
    BigDecimal sum = new BigDecimal( 0);
    
    public ThreadManager( int lower, int upper, int mc, int incr, int numThreads) {

        threads = new AbstractThread[numThreads];
        this.lower = lower;
        this.upper = upper;
        this.iMc = mc;
        this.incr = incr;
        this.numThreads = numThreads;

        try {

            OutputStream output = new FileOutputStream("c:/Users/Spencer/Documents/AACode/ignore.txt");
            PrintStream printOut = new PrintStream(output);

            System.setErr(printOut);
        }
        catch (Exception e) {

            System.out.println( e);
        }
    }

    public ThreadManager( int lower, int upper, String mc, int incr, int numThreads) {

        threads = new AbstractThread[numThreads];
        this.lower = lower;
        this.upper = upper;
        this.sMc = mc;
        this.incr = incr;
        this.numThreads = numThreads;
    }

    public void run() {

        //imma assume that the difference between upper and lower is a multiple of incr for now
        for( int i = lower; i < upper;) {

            for( int j = 0; j < numThreads && i <= upper - incr; j++) {

                if( sMc == null) {

                    threads[j] = new ComputeThread( i, i + incr, iMc);
                }
                else {

                    threads[j] = new ComputeThread( i, i + incr, sMc);
                }
                threads[j].init();
                //System.out.println( "Thread " + j + " started with bounds [ " + i + ", " + (i + incr) + "]");

                i += incr;
                //System.out.println( (int)(( i / (double)upper) * 1000) / 10.0 + "%");
            }

            for(int j = 0; j < numThreads; j++) {

               
                if( threads[j] != null) {
                    
                    //System.out.println( threads[j].isDone && threads[j].started);
                    
                    if(threads[j].isDone && threads[j].started) {

                        sum = sum.add( threads[j].getSum());
                    }
                    else {

                        //volatile boolean isDone = threads[j].isDone;
                        while(!threads[j].isDone) {
                            
                            //System.out.println( "made it here");
                            //System.err.println( threads[j].isDone && threads[j].started);//I have to print or it will not work consistently
                            //System.out.println("hellO");
                            //threads[j].thread.join(1);
                            //threads[j].join();
                        }

                        sum = sum.add( threads[j].getSum());
                    }
                }
            }

            //System.out.println(i);
            System.out.println( (int)(( i / (double)upper) * 1000) / 10.0 + "%");
            try { 

                FileWriter fw = new FileWriter("pi.txt");
                fw.flush();
                fw.write("Approximate accurate digits: " + ( i * 7) + "\n" + AbstractThread.extractPi(sum));
            }
            catch( Exception e) {

                System.out.println( e);
            }
        }

        System.out.println( AbstractThread.extractPi( sum));
    }
}