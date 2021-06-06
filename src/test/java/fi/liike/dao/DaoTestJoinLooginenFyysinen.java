package fi.liike.dao;

import org.junit.After;
import org.junit.Before;

import fi.liike.rest.api.JoinCategory;

public class DaoTestJoinLooginenFyysinen {

	private MainDaoTester mainTester;

	@Before
	public void setUp() throws Exception {
		mainTester = new MainDaoTester(JoinCategory.LOOGINENFYYSINEN);
	}

	@After
	public void tearDown() throws Exception {
		mainTester.clear();
	}

}
