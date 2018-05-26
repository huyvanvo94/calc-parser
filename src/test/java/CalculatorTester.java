import org.junit.Before;
import org.junit.Test;
import parser.CalcParser;

import static org.junit.Assert.assertEquals;
public class CalculatorTester {

    CalcParser parser;

    @Before
    public void init(){

        parser = new CalcParser();

    }

    @Test
    public void test1(){
        parser.parse("1-1");

        double result = parser.solve();

        System.out.println(result);
        assertEquals(parser.solve(), 0.0, 0.001 );
    }
}
