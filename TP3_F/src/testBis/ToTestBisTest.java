package testBis;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tempBis.MockConversion;
import tempBis.ToTestBis;

public class ToTestBisTest {
	
	private MockConversion conv;
	private ToTestBis tt;
	
	@Before
	public void setUp() throws Exception {
		conv = new MockConversion();
		tt = new ToTestBis(conv);
	}

	@After
	public void tearDown() throws Exception {
		tt = null;
		conv = null;
	}

	@Test
	public void testConvertC2F1() {
		double expected = 32;
		double value = tt.convert(0, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertC2F2() {
		double expected = 100;
		double value = tt.convert(212, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertC2F3() {
		double expected = 37;
		double value = tt.convert(98.6, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertC2F4() {
		double expected = -40;
		double value = tt.convert(-40, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C1() {
		double expected = 0;
		double value = tt.convert(32, "F2C");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C2() {
		double expected = 212;
		double value = tt.convert(100, "F2C");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C3() {
		double expected = 98.6;
		double value = tt.convert(37, "F2C");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C4() {
		double expected = -40;
		double value = tt.convert(-40, "F2C");
		assertTrue(expected == value);
	}

}
