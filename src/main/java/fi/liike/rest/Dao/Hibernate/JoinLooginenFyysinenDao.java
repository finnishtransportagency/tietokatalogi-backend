package fi.liike.rest.Dao.Hibernate;

import org.hibernate.Session;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinLooginenFyysinen;
import fi.liike.rest.Model.JoinLooginenFyysinenHistory;

public class JoinLooginenFyysinenDao extends JoinMainDao implements JoinDao {

	private Integer parentNode;

	@Override
	public JoinDao getDao(int parentNode, String remoteUser) {
		this.parentNode = parentNode;
		this.remoteUser = remoteUser;
		return this;
	}

	@Override
	public void save(Session session, int childNode) {
		super.createEntry(session, new JoinLooginenFyysinen(childNode, parentNode), new JoinLooginenFyysinenHistory());
	}

	@Override
	public Integer getParentNodeId(int childNodeId) {
		return super.getParentNodeId(new JoinLooginenFyysinen(childNodeId));
	}

	@Override
	public void update(Session session, int childNode) {
		super.update(session, new JoinLooginenFyysinen(childNode, parentNode), new JoinLooginenFyysinenHistory());
	}

	@Override
	public void delete(Session session, int childNode) {
		super.delete(session, new JoinLooginenFyysinen(childNode, parentNode), new JoinLooginenFyysinenHistory());
	}

}
