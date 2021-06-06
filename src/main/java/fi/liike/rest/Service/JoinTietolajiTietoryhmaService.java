package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.JoinTietolajiTietoryhmaDao;

public class JoinTietolajiTietoryhmaService implements JoinService {

	private JoinDao dao;

	public JoinTietolajiTietoryhmaService() {
		dao = new JoinTietolajiTietoryhmaDao();
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

