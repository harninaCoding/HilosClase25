package test.hilos;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BarcoHiloDosCardumenTest.class, BarcoHiloUnCardumenTest.class, CardumenHillosTest.class })
public class AllTestsHilos {

}
