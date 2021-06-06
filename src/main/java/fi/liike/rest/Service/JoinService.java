package fi.liike.rest.Service;

import fi.liike.rest.Dao.JoinPublicDao;

public interface JoinService {

	JoinPublicDao getDao(int parentNode, String remoteUser);

	Integer getParentNodeId(int childNode);
}
