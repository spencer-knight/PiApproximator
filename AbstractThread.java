import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

//run function, summation/f(x), retrieveBigint, variable to keep track of when output is ready

public class AbstractThread {
    
    public int lower = 0;
    public Thread thread = null;
    public int upper = 100;
    public volatile boolean isDone = false;
    public static BigDecimal con = new BigDecimal( 0);
    public BigDecimal endSum = new BigDecimal( -1);
    public volatile boolean started = false;
    public static MathContext mc = new MathContext( 100);

    public AbstractThread( int lower, int upper, int mc) {

        this.lower = lower;
        this.upper = upper;

        this.mc = new MathContext( mc);
    }

    public void start() {

        
    }

    public void close() {


    }

    public AbstractThread( int lower, int upper, String mc) {

        this.lower = lower;
        this.upper = upper;

        this.mc = new MathContext( mc);
    }

    public BigDecimal getSum() {

        return endSum;
    }

    public BigDecimal f( BigDecimal k) {

        BigDecimal ret = k.multiply( new BigDecimal(4), mc);
        
        ret = factorial(ret);

        ret = ret.divide( factorial(k).pow( 4, mc), mc);

        ret = ret.multiply( k.multiply( new BigDecimal( 26390), mc).add( new BigDecimal( 1103, mc)));

        ret = ret.divide( new BigDecimal(396).pow( k.intValue() * 4), mc);

        return ret;
    }

    public BigDecimal summation() {

        BigDecimal sum = new BigDecimal( 0);

        for( int i = lower; i < upper; i++) {

            //System.out.println("this is called");
            sum = sum.add( f( new BigDecimal( i)));
            //System.out.println( sum);
        }

        return sum;
    }

    public void run() {

        started = true;
        endSum = summation();
        isDone = true;
        //when this is done, set isDone to true
    }

    public static void calculateConstant() {

        con = new BigDecimal( 2);
        con = con.sqrt( mc);
        con = con.multiply( new BigDecimal( 2));
        con = con.divide( new BigDecimal( Math.pow( 99, 2)), mc);
    }

    public static BigDecimal factorial( BigDecimal in) {

        BigInteger x = in.toBigInteger();
        BigInteger product = x;

        if( x.equals( BigInteger.ZERO)) {

            return new BigDecimal( 1);
        }
        x = x.subtract( BigInteger.ONE);
        

        while(  !(x.equals(BigInteger.ONE) || x.equals(BigInteger.ZERO))) {

            product = product.multiply( x);
            x = x.subtract( BigInteger.ONE);
        }

        return new BigDecimal( product);
    }

    public static String extractPi( BigDecimal in) {

        calculateConstant();
        String ret = in.multiply( con, mc).pow( -1, mc).toString();
        return ret.substring(0, ret.length() - 1);
    }

    public void init() {


    }

    public void join() {


    }
}

//print completion