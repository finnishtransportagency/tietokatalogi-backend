package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.JoinTietolajiLooginenDao;

public class JoinTietolajiLooginenService implements JoinService {

	private JoinDao dao;

	public JoinTietolajiLooginenService() {
		dao = new JoinTietolajiLooginenDao();
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
