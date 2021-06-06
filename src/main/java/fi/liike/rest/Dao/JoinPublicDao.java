package fi.liike.rest.Dao;

import org.hibernate.Session;

public interface JoinPublicDao {

	void save(Session session, int childNode);

	void update(Session session, int childNode);

	void delete(Session session, int childNode);
}
