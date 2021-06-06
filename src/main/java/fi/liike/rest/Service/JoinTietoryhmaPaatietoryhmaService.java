package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.Hibernate.JoinTietoryhmaPaatietoryhmaDao;

public class JoinTietoryhmaPaatietoryhmaService implements JoinService {

	private JoinDao dao;

	public JoinTietoryhmaPaatietoryhmaService() {
		dao = new JoinTietoryhmaPaatietoryhmaDao();
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
