package fi.liike.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fi.liike.rest.api.JoinCategory;

public class TestJoinLooginenFyysinenService {

	private MainJoinServiceTester mainTester;

	@Before
	public void setUp() throws Exception {
		mainTester = new MainJoinServiceTester(JoinCategory.LOOGINENFYYSINEN);
	}

	@After
	public void tearDown() throws Exception {
		mainTester.clear();
	}

	@Test
	public void testCreateEntry() {
		mainTester.testCreateEntry(JoinCategory.LOOGINENFYYSINEN);
	}

}
