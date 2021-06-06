package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.JoinLooginenFyysinenDao;

public class JoinLooginenFyysinenService implements JoinService {

	private JoinDao dao;

	public JoinLooginenFyysinenService() {
		dao = new JoinLooginenFyysinenDao();
	}

	@Override
	public JoinPublicDao getDao(int parentNode, String remoteUser) {
		return dao.getDao(parentNode, remoteUser);
	}

	@Override
	public Integer getParentNodeId(int childNode) {
		return dao.getParentNodeId(childNode);
	}

}
