package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import temp.MockConversion;
import temp.ToTest;

public class toTestTest {

	private MockConversion conv;
	private ToTest tt;
	
	@Before
	public void setUp() throws Exception {
		conv = new MockConversion();
		tt = new ToTest(conv);
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
		double value = tt.convert(32, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C2() {
		double expected = 212;
		double value = tt.convert(100, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C3() {
		double expected = 98.6;
		double value = tt.convert(37, "C2F");
		assertTrue(expected == value);
	}
	
	@Test
	public void testConvertF2C4() {
		double expected = -40;
		double value = tt.convert(-40, "C2F");
		assertTrue(expected == value);
	}


}
