package fi.liike.rest.Dao;

import fi.liike.rest.util.HibernateUtil;
import org.hibernate.Session;

public class HibernateSession {
    private Session session;

    public HibernateSession() {}

    protected Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    protected void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    protected boolean isTestingSession() {
        return HibernateUtil.isTestingSession();
    }

}
