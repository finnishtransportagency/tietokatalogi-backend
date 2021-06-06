package fi.liike.rest.Dao.Hibernate;

import org.hibernate.Session;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinTietoryhmaPaatietoryhma;
import fi.liike.rest.Model.JoinTietoryhmaPaatietoryhmaHistory;

public class JoinTietoryhmaPaatietoryhmaDao extends JoinMainDao implements JoinDao {
	private Integer parentNode;

	@Override
	public JoinDao getDao(int parentNode, String remoteUser) {
		this.parentNode = parentNode;
		this.remoteUser = remoteUser;
		return this;
	}

	@Override
	public void save(Session session, int childNode) {
		super.createEntry(session, new JoinTietoryhmaPaatietoryhma(childNode, parentNode),
				new JoinTietoryhmaPaatietoryhmaHistory());
	}

	@Override
	public Integer getParentNodeId(int childNodeId) {
		return super.getParentNodeId(new JoinTietoryhmaPaatietoryhma(childNodeId));
	}

	@Override
	public void update(Session session, int childNode) {
		super.update(session, new JoinTietoryhmaPaatietoryhma(childNode, parentNode),
				new JoinTietoryhmaPaatietoryhmaHistory());
	}

	@Override
	public void delete(Session session, int childNode) {
		super.delete(session, new JoinTietoryhmaPaatietoryhma(childNode, parentNode),
				new JoinTietoryhmaPaatietoryhmaHistory());
	}

}
