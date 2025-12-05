package test.hilos;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BarcoHiloDosCardumenTest.class, HiloDosBarcosUnCardumenTest.class, CardumenHillosTest.class,HilosMundoTest.class })
public class AllTestsHilos {

}
