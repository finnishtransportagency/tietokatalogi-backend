package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinTietoryhmaTietovaranto;
import fi.liike.rest.Model.JoinTietoryhmaTietovarantoHistory;
import org.hibernate.Session;

public class JoinTietoryhmaTietovarantoDao extends JoinMainDao implements JoinDao {
    private Integer parentNode;

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        this.parentNode = parentNode;
        this.remoteUser = remoteUser;
        return this;
    }

    @Override
    public void save(Session session, int childNode) {
        super.createEntry(session, new JoinTietoryhmaTietovaranto(childNode, parentNode),
                new JoinTietoryhmaTietovarantoHistory());
    }

    @Override
    public Integer getParentNodeId(int childNodeId) {
        return super.getParentNodeId(new JoinTietoryhmaTietovaranto(childNodeId));
    }

    @Override
    public void update(Session session, int childNode) {
        super.update(session, new JoinTietoryhmaTietovaranto(childNode, parentNode),
                new JoinTietoryhmaTietovarantoHistory());
    }

    @Override
    public void delete(Session session, int childNode) {
        super.delete(session, new JoinTietoryhmaTietovaranto(childNode, parentNode),
                new JoinTietoryhmaTietovarantoHistory());
    }
}
