package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.HibernateDao;
import fi.liike.rest.Model.Frontpage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;

public class FrontpageDao extends HibernateDao {
    private final Logger LOG = LoggerFactory.getLogger(FrontpageDao.class);

    public void save(Frontpage saveContent) {
        Session session = getSession();
        Transaction transaction = null;
        saveContent.setId(1);
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(saveContent);
            transaction.commit();
            LOG.debug("Persisted frontpage successfully");
        } catch (RuntimeException e) {
            this.logDbException(e);
            try {
                if (transaction != null) transaction.rollback();
            } catch (RuntimeException ex) {
                LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
            }
        } finally {
            if (session != null)
                session.close();
        }
    }
}
