package fi.liike.service;

import fi.liike.rest.Model.JoinLooginenFyysinen;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.Service.JoinLooginenFyysinenService;
import fi.liike.rest.Service.JoinService;
import fi.liike.rest.api.JoinCategory;
import fi.liike.testutils.TestDbUtil;

public class MainJoinServiceTester {

	public MainJoinServiceTester(JoinCategory category) {
		TestDbUtil.init();
		switch (category) {
		case LOOGINENFYYSINEN:
			TestDbUtil.setupJoinTableDB(JoinCategory.LOOGINENFYYSINEN);
			TestDbUtil.clearJoinTableDB(JoinCategory.LOOGINENFYYSINEN);
			break;
		default:
			break;
		}
	}

	public void clear() {
		TestDbUtil.clearJoinTableDB(JoinCategory.LOOGINENFYYSINEN);
	}

	public void testCreateEntry(JoinCategory category) {
	}

	@SuppressWarnings("unused")
	private JoinService getService(JoinCategory category) {
		switch (category) {
		case LOOGINENFYYSINEN:
			return new JoinLooginenFyysinenService();
		default:
			return null;
		}
	}

	@SuppressWarnings("unused")
	private Class<? extends JoinTable> getJoinClass(JoinCategory category) {
		switch (category) {
		case LOOGINENFYYSINEN:
			return JoinLooginenFyysinen.class;
		default:
			return null;
		}
	}

}