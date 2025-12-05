package test.modelo;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	BarcoTest.class,CardumenTest.class,PuertoTest.class
})
class allTest {

}
