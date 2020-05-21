public class Main {
 
    public static void main( String[] args) {

        //public ThreadManager( int lower, int upper, int mc, int incr, int numThreads)
        ThreadManager tm = new ThreadManager(0, 14290, 100000, 1, 10);
        tm.run();
    }
}