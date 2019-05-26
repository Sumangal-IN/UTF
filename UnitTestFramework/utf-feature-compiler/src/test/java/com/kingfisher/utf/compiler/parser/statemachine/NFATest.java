package com.kingfisher.utf.compiler.parser.statemachine;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class NFATest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsTransitionValidTrue() throws IOException {
		assertTrue(new NFA().isTransitionValid("start", "setup"));
	}

	@Test
	public void testIsTransitionValidFalse() throws IOException {
		assertFalse(new NFA().isTransitionValid("start", "then"));
	}

	@Test
	public void testIsTransitionValidFalse2() throws IOException {
		assertFalse(new NFA().isTransitionValid("xyz", "abc"));
	}

	@Test
	public void testIsStateIgnorableTrue() throws IOException {
		assertTrue(new NFA().isStateIgnorable("comment"));
	}

	@Test
	public void testIsStateIgnorableFalse() throws IOException {
		assertFalse(new NFA().isStateIgnorable("setup"));
	}

	@Test
	public void testIsStateAcceptableTrue() throws IOException {
		assertTrue(new NFA().isStateAcceptable("end"));
	}

	@Test
	public void testIsStateAcceptableFalse() throws IOException {
		assertFalse(new NFA().isStateAcceptable("teardown"));
	}

	@Test
	public void testIsStateValidTrue() throws IOException {
		assertTrue(new NFA().isStateValid("when"));
	}

	@Test
	public void testIsStateValidFalse() throws IOException {
		assertFalse(new NFA().isStateValid("abc"));
	}

}
