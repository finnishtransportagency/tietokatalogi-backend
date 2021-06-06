package fi.liike.rest.Dao;

public interface JoinDao extends JoinPublicDao {

	JoinDao getDao(int parentNode, String remoteUser);

	Integer getParentNodeId(int childNode);

}
