package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
        C01PostBooking.class,
        C02GetBooking.class,
        C03UpdateBooking.class,
        C04PartiallyUpdateBooking.class,
        C05DeleteBooking.class ,
        C06NegativeGetTest.class})
public class Runner {

}
