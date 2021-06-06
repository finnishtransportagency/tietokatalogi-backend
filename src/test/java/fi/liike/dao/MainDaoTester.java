package fi.liike.dao;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Dao.Hibernate.JoinLooginenFyysinenDao;
import fi.liike.rest.Model.JoinLooginenFyysinen;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.api.JoinCategory;
import fi.liike.testutils.TestDbUtil;

public class MainDaoTester {

	public MainDaoTester(JoinCategory category) {
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

	@SuppressWarnings("unused")
	private JoinDao getDao(JoinCategory category) {
		switch (category) {
		case LOOGINENFYYSINEN:
			return new JoinLooginenFyysinenDao();
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
