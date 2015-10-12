package testJMockit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import tempJMockit.IConversion;
import tempBis.MockConversion;
import tempBis.ToTestBis;
import tempJMockit.ToTestJMockit;

public class TestToTestJMockit {

	@Mocked 
	private IConversion ic;
	private ToTestJMockit ttj;
	
	@Before
	public void setUp() throws Exception {
		ttj = new ToTestJMockit(ic);
	}

	@After
	public void tearDown() throws Exception {
		ttj = null;
	}

	@Test
	public void testConvertC2F1() {
		new Expectations (){{
			ic.convC2F(0);
			result=32;
		}};
		double expected = 32;
		double value = ttj.convert(0, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertC2F2() {
		new Expectations (){{
			ic.convC2F(212);
			result=100;
		}};
		double expected = 100;
		double value = ttj.convert(212, "C2F");
		assertTrue(expected == value);
	}
	
	/*@Test
	public void testConvertC2F2WithVerfication() {
		double expected = 100;
		double value = ttj.convert(212, "C2F");
		new Verifications(){{
			ic.convC2F(212);
			double result = 100;
		}};
		//double expected = 100;
		//double value = ttj.convert(212, "C2F");
		assertTrue(expected == value);
	}*/
	
	@Test
	public void testConvertC2F3() {
		new Expectations (){{
			ic.convC2F(98.6);
			result=37;
		}};
		double expected = 37;
		double value = ttj.convert(98.6, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertC2F4() {
		new Expectations (){{
			ic.convC2F(-40);
			result=-40;
		}};
		double expected = -40;
		double value = ttj.convert(-40, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C1() {
		new NonStrictExpectations (){{
			ic.convF2C(32);
			result=0;
		}};
		double expected = 0;
		double value = ttj.convert(32, "F2C");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C2() {
		new Expectations (){{
			ic.convF2C(100);
			result=212;
		}};
		double expected = 212;
		double value = ttj.convert(100, "F2C");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C3() {
		new Expectations (){{
			ic.convF2C(37);
			result=98.6;
		}};
		double expected = 98.6;
		double value = ttj.convert(37, "F2C");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C4() {
		new Expectations (){{
			ic.convF2C(-40);
			result=-40;
		}};
		double expected = -40;
		double value = ttj.convert(-40, "F2C");
		assertTrue(expected == value);
	}


}
