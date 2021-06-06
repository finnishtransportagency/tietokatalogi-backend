package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.HibernateDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.HistoryType;
import fi.liike.rest.api.dto.FetchHenkiloRooliDto;
import fi.liike.rest.api.dto.HenkiloConverter;
import fi.liike.rest.api.dto.HenkiloDto;
import fi.liike.rest.api.dto.RooliDto;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

import static java.lang.String.format;

public class HenkiloDao extends HibernateDao {

    private final static String henkiloQuery = "SELECT h.tunnus, h.object_id, h.nayttonimi, h.tunnustyyppi,\n" +
            "h.poistunut, h.kayttajatunnus, h.yritys, h.yritystunnus, h.etunimi, h.sukunimi, h.sahkoposti, h.matkapuhelin\n" +
            "FROM tietok.HENKILO h\n";

    private final static String rooliQuery = "SELECT r.tunnus, r.nimi FROM tietok.ROOLI r\n";
    private final Logger LOG = LoggerFactory.getLogger(HenkiloDao.class);

    public List<Henkilo> saveHenkiloList(List<Henkilo> henkiloList) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (Henkilo henkilo : henkiloList) {
                session.save(henkilo);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            try {
            	LOG.error("Unable to do a transaction. Error message: " + e.getMessage());
                LOG.error("There was an error: " + e.getMessage());
                if (e instanceof JDBCException) {
                    JDBCException jdbcException = (JDBCException) e;
                    LOG.error(jdbcException.getSQLException().toString());
                    LOG.error(jdbcException.getSQL());
                }
                transaction.rollback();
            } catch (RuntimeException ex) {
            	LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
            }
        } finally {
            if (session != null)
                session.close();
        }
        return henkiloList;
    }

    public JoinHenkiloRooliTable saveHenkiloRooli(Session session, JoinHenkiloRooliTable henkiloRooli, JoinHenkiloRooliTableHistory history) throws SQLException {
        if (henkiloRooli.getRivitunnus() != null) {
            LOG.error("Uusi rivi voidaan luoda vain jos id:ta ei ole maaritelty.");
            throw new SQLException("Uusi rivi voidaan luoda vain jos id:ta ei ole maaritelty.");
        }
        List<JoinHenkiloRooliTable> joinHenkiloRooliTableList = getJoinHenkiloRooliList(session, henkiloRooli);
        if (!joinHenkiloRooliTableList.isEmpty()) {
            LOG.error("Cannot save duplicate role with the same person");
            closeSession();
            throw new RuntimeException();
        }
        return (JoinHenkiloRooliTable) this.save(session, henkiloRooli, history);
    }

    public void saveHenkiloRooliList(Session session, Set<? extends JoinHenkiloRooliTable> saveList,
                                       Class<? extends JoinHenkiloRooliTableHistory> historyClass) throws SQLException {
        JoinHenkiloRooliTableHistory history;
        for (JoinHenkiloRooliTable henkiloRooli : saveList) {
            if (historyClass.equals(JarjestelmaHenkiloRooliHistory.class)) {
                history = new JarjestelmaHenkiloRooliHistory();
            } else if (historyClass.equals(SovellusHenkiloRooliHistory.class)) {
                history = new SovellusHenkiloRooliHistory();
            } else {
                return;
            }
            saveHenkiloRooli(session, henkiloRooli, history);
        }
    }

    public void deleteHenkiloRooliList(Session session, Set<? extends JoinHenkiloRooliTable> deleteList,
                                       Class<? extends JoinHenkiloRooliTableHistory> historyClass) throws SQLException {
        JoinHenkiloRooliTableHistory history;
        for (JoinHenkiloRooliTable henkiloRooli : deleteList) {
            if (historyClass.equals(JarjestelmaHenkiloRooliHistory.class)) {
                history = new JarjestelmaHenkiloRooliHistory();
            } else if (historyClass.equals(SovellusHenkiloRooliHistory.class)) {
                history = new SovellusHenkiloRooliHistory();
            } else {
                return;
            }
            deleteHenkiloRooli(session, henkiloRooli, history);
        }
    }


    //HOX! Transaction already started
    private void deleteHenkiloRooli(Session session, JoinHenkiloRooliTable henkiloRooli, JoinHenkiloRooliTableHistory history) throws SQLException {
        Criteria criteria = session.createCriteria(henkiloRooli.getClass());
        String systemIdFieldName;
        if (henkiloRooli.getClass().equals(JarjestelmaHenkiloRooli.class)) {
            systemIdFieldName = JarjestelmaHenkiloRooli.systemIdFieldName;
        } else {
            systemIdFieldName = SovellusHenkiloRooli.systemIdFieldName;
        }
        criteria.add(Restrictions.eq("henkiloId", henkiloRooli.getHenkiloId()));
        criteria.add(Restrictions.eq("rooliId", henkiloRooli.getRooliId()));
        criteria.add(Restrictions.eq(systemIdFieldName, henkiloRooli.getSysteemiId()));

        JoinHenkiloRooliTable entry = (JoinHenkiloRooliTable) criteria.uniqueResult();
        if (entry == null) {
            LOG.error("Could not delete HenkiloRooli. HenkiloId {}, rooliId {}, systemId {} not found.", henkiloRooli.getHenkiloId(), henkiloRooli.getRooliId(), henkiloRooli.getSysteemiId());
            throw new SQLException("Poistettavaa ei loydy");
        }

        if (!setUpHistoryEntry(henkiloRooli, history, HistoryType.DEL)) {
            throw new SQLException("Could not set up history entry");
        }

        session.delete(entry);
    }

    public Rooli saveRooli(Rooli rooli) throws SQLException {
        if (rooli.getTunnus() != null) {
            throw new SQLException("Uusi rivi voidaan luoda vain jos id:ta ei ole maaritelty.");
        }
        List<Rooli> rooliList = getRooliListByName(getSession(), rooli);
        if (!rooliList.isEmpty()) {
            LOG.error("Role already exists: " + rooli.getNimi());
            closeSession();
            throw new RuntimeException();
        }
        return (Rooli) this.save(null, rooli, null);
    }

    private Object save(Session session, Object content, JoinHenkiloRooliTableHistory history) {
        if (content.getClass().getSuperclass().equals(JoinHenkiloRooliTable.class)) {
            if (!setUpHistoryEntry((JoinHenkiloRooliTable) content, history, HistoryType.ADD)) {
                return null;
            }
        }

        if (session == null) {
            session = getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(content);
                if (history != null) {
                    session.save(history);
                }
                transaction.commit();
            } catch (RuntimeException e) {
                try {
                    LOG.error("Unable to do a delete transaction. Error message: " + e.getMessage());
                    content = null;
                    transaction.rollback();
                } catch (RuntimeException ex) {
                    LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
                }
            } finally {
                if (session != null)
                    session.close();
            }
        } else {
            session.save(content);
        }
        return content;
    }

    public List<Henkilo> getHenkiloListByRooliAndSystemId(
            Integer rooliId, Integer systeemiId, Boolean filterDisabled, Class<? extends JoinHenkiloRooliTable> joinClass,
            String joinIdColumn) {

        Session session = getSession();
        session.beginTransaction();
        List<Henkilo> henkiloList = getHenkiloListByRooliAndSystemId(session, rooliId, systeemiId, filterDisabled, joinClass, joinIdColumn);
        closeSession();
        return henkiloList;
    }

    private List<Henkilo> getHenkiloListByRooliAndSystemId(Session session,
            Integer rooliId, Integer systeemiId, Boolean filterDisabled, Class<? extends JoinHenkiloRooliTable> joinClass,
            String joinIdColumn) {
        String table = joinClass.getAnnotation(Table.class).name();

        String sql = format("%s INNER JOIN tietok.%s jhr ON jhr.HENKILO_ID = h.TUNNUS\n" +
                "WHERE jhr.ROOLI_ID = %d AND jhr.%s = %d\n", henkiloQuery, table, rooliId, joinIdColumn, systeemiId);
        if (filterDisabled) {
            sql += "AND h.poistunut = 0";
        }
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> henkiloObjList = query.list();
        List<Henkilo> henkiloList = new ArrayList<Henkilo>();
        for (Object[] henkiloObj : henkiloObjList) {
            henkiloList.add(mapHenkiloRows(henkiloObj));
        }
        return henkiloList;
    }

    public Map<Integer, Set<FetchHenkiloRooliDto>> getConvertedFetchHenkRooliSetSystemIdMap(
            Map<Integer, Set<Rooli>> rooliSetSystemIdMap, Boolean filterDisabled,
            Class<? extends JoinHenkiloRooliTable> joinClass, String joinTableIdColumn, HenkiloConverter henkiloConverter) {
        Session session = getSession();
        session.beginTransaction();
        Map<Integer, Set<FetchHenkiloRooliDto>> fetchHenkRooliSetSystemIdMap = new HashMap<>();

        for (Integer systemId : rooliSetSystemIdMap.keySet()) {
            Set<Rooli> systemRooliList = rooliSetSystemIdMap.get(systemId);
            Set<FetchHenkiloRooliDto> henkiloRooliList = new HashSet<>();
            for (Rooli rooli : systemRooliList) {
                List<Henkilo> henkiloList = getHenkiloListByRooliAndSystemId(session, rooli.getTunnus(), systemId, filterDisabled,
                        joinClass, joinTableIdColumn);
                for (Henkilo henkilo : henkiloList) {
                    RooliDto rooliDto = henkiloConverter.rooliModelToDto(rooli);
                    HenkiloDto henkiloDto = henkiloConverter.henkiloModelToDto(henkilo);
                    henkiloRooliList.add(new FetchHenkiloRooliDto(rooliDto, henkiloDto));
                }
            }
            fetchHenkRooliSetSystemIdMap.put(systemId, henkiloRooliList);
        }

        closeSession();
        return fetchHenkRooliSetSystemIdMap;
    }

    public List<Henkilo> getHenkiloList(String propertyName, String propertyValue, Boolean allowDisabled) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Henkilo.class);
        if (propertyName != null && !propertyName.isEmpty()) {
            criteria.add(Restrictions.eq(propertyName, propertyValue));
        } else if (propertyValue != null && propertyValue.length() > 0) {
            String[] splittedValue = propertyValue.split(" ");
            String name1 = splittedValue[0];
            name1 = format("%s%s%s", "%", name1, "%");

            if (splittedValue.length > 1) {
                String name2 = splittedValue[1];
                name2 = format("%s%s%s", "%", name2, "%");

                Criterion crit1 = Restrictions.and(
                        Restrictions.ilike("etunimi", name1),
                        Restrictions.ilike("sukunimi", name2));
                Criterion crit2 = Restrictions.and(
                        Restrictions.ilike("etunimi", name2),
                        Restrictions.ilike("sukunimi", name1));
                criteria.add(Restrictions.or(crit1, crit2));
            } else {
                Criterion crit1 = Restrictions.or(
                        Restrictions.ilike("etunimi", name1),
                        Restrictions.ilike("sukunimi", name1)
                );
                criteria.add(crit1);
            }
        }
        if (!allowDisabled) {
            criteria.add(Restrictions.eq("poistunut", 0));
        }
        List<Henkilo> henkiloList = criteria.list();
        closeSession();
        return henkiloList;
    }

    public Henkilo getHenkiloByKayttajatunnus(String kayttajatunnus) {
        Criteria criteria = getSession().createCriteria(Henkilo.class);
        criteria.add(Restrictions.eq("kayttajatunnus", kayttajatunnus));
        List<Henkilo> henkiloList = criteria.list();
        closeSession();
        if (henkiloList.size() > 1) {
            LOG.warn("Found more than one Henkilo with kayttajatunnus {}", kayttajatunnus);
            return henkiloList.get(0);
        } else if(henkiloList.size() == 1) {
            return henkiloList.get(0);
        } else {
            LOG.error("No Henkilo found by kayttajatunnus {}", kayttajatunnus);
            return null;
        }
    }

    private Set<Rooli> getRooliListBySystemId(Integer systemId, Class<? extends JoinHenkiloRooliTable> joinClass,
                                              String jointableIdColumn, Session session) {
        String table = joinClass.getAnnotation(Table.class).name();
        String sql = format("%s INNER JOIN tietok.%s jhr ON jhr.ROOLI_ID = r.tunnus\n" +
                "WHERE jhr.%s = %d", rooliQuery, table, jointableIdColumn, systemId);

        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> rooliObjList = query.list();

        Set<Rooli> roolis = new HashSet<Rooli>();
        for (Object[] rooliObj : rooliObjList) {
            roolis.add(mapRooliRows(rooliObj));
        }

        return roolis;
    }

    public Map<Integer, Set<Rooli>> getRooliSetSystemIdMap(Set<Integer> systemIds, Class<? extends JoinHenkiloRooliTable> joinClass,
                                                           String jointableIdColumn) {
        Map<Integer, Set<Rooli>> systemIdToRoolisSet = new HashMap<>();

        Session session = getSession();
        session.beginTransaction();
        for (Integer systemId : systemIds) {
            Set<Rooli> rooliList = getRooliListBySystemId(systemId, joinClass, jointableIdColumn, session);
            systemIdToRoolisSet.put(systemId, rooliList);
        }

        closeSession();
        return systemIdToRoolisSet;
    }

    public Rooli getRooliById(Integer rooliId) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Rooli.class);
        criteria.add(Restrictions.eq("tunnus", rooliId));
        Rooli rooli = (Rooli) criteria.uniqueResult();
        closeSession();
        return rooli;
    }

    public Rooli getRooliByName(String rooliName) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Rooli.class);
        criteria.add(Restrictions.eq("nimi", rooliName));
        Rooli rooli = (Rooli) criteria.uniqueResult();
        closeSession();
        return rooli;
    }

    public Henkilo getHenkiloById(Integer henkiloId) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Henkilo.class);
        criteria.add(Restrictions.eq("tunnus", henkiloId));
        Henkilo henkilo = (Henkilo) criteria.uniqueResult();
        closeSession();
        return henkilo;
    }

    public Henkilo getHenkiloByObjectId(String objectId) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(Henkilo.class);
        criteria.add(Restrictions.eq("objectID", objectId));
        Henkilo henkilo = (Henkilo) criteria.uniqueResult();
        closeSession();
        return henkilo;
    }

    private List<JoinHenkiloRooliTable> getJoinHenkiloRooliList(Session session, JoinHenkiloRooliTable joinHenkiloRooli) {
        String systemIdFieldName;
        if (joinHenkiloRooli.getClass().equals(JarjestelmaHenkiloRooli.class)) {
            systemIdFieldName = JarjestelmaHenkiloRooli.systemIdFieldName;
        } else {
            systemIdFieldName = SovellusHenkiloRooli.systemIdFieldName;
        }
        // This method can be called from inside an existing transaction. In that case, skip transaction management.
        if (session != null)
            return getJoinHenkiloRooliListResults(session, joinHenkiloRooli, systemIdFieldName);

        List<JoinHenkiloRooliTable> joinHenkiloRooliListResults = new ArrayList<>();
        session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            joinHenkiloRooliListResults.addAll(getJoinHenkiloRooliListResults(session, joinHenkiloRooli, systemIdFieldName));
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                LOG.error("Unable to do a transaction. Error message: " + e.getMessage());
                transaction.rollback();
            } catch (RuntimeException ex) {
                LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
            }
        } finally {
            if (session != null)
                session.close();
        }

        return joinHenkiloRooliListResults;
    }

    private List<JoinHenkiloRooliTable> getJoinHenkiloRooliListResults(
            Session session, JoinHenkiloRooliTable joinHenkiloRooli, String systemIdFieldName) {
        Criteria criteria = session.createCriteria(joinHenkiloRooli.getClass());
        criteria.add(Restrictions.eq("henkiloId", joinHenkiloRooli.getHenkiloId()));
        criteria.add(Restrictions.eq("rooliId", joinHenkiloRooli.getRooliId()));
        criteria.add(Restrictions.eq(systemIdFieldName, joinHenkiloRooli.getSysteemiId()));

        return criteria.list();
    }


    public List<JoinHenkiloRooliTable> getJoinHenkiloRooliListByHenkiloId(
            Integer tunnus, Class<? extends Haettava> systemClass, Session session) {
        Class<? extends JoinHenkiloRooliTable> joinHenkiloRooliClass;

        if (systemClass.equals(Jarjestelma.class)) {
            joinHenkiloRooliClass = JarjestelmaHenkiloRooli.class;
        } else {
            joinHenkiloRooliClass = SovellusHenkiloRooli.class;
        }

        Criteria criteria = session.createCriteria(joinHenkiloRooliClass);
        criteria.add(Restrictions.eq("henkiloId", tunnus));

        return criteria.list();
    }

    private List<Rooli> getRooliListByName(Session session, Rooli rooli) {
        Criteria criteria = session.createCriteria(rooli.getClass());
        criteria.add(Restrictions.eq("nimi", rooli.getNimi()));
        return criteria.list();
    }

    private Henkilo mapHenkiloRows(Object[] henkiloObj) {
        Integer tunnus = ((BigDecimal) henkiloObj[0]).intValue();
        String objectId = (String) henkiloObj[1];
        String nayttonimi = (String) henkiloObj[2];
        String tunnustyyppi = (String) henkiloObj[3];
        Integer poistunut = ((Short) henkiloObj[4]).intValue();
        String kayttajatunnus = (String) henkiloObj[5];
        String yritys = (String) henkiloObj[6];
        String yritystunnus = (String) henkiloObj[7];
        String etunimi = (String) henkiloObj[8];
        String sukunimi = (String) henkiloObj[9];
        String sahkoposti = (String) henkiloObj[10];
        String matkapuhelin = (String) henkiloObj[11];
        return new Henkilo(tunnus, objectId, nayttonimi, tunnustyyppi, poistunut, kayttajatunnus, yritys,
                yritystunnus, etunimi, sukunimi, sahkoposti, matkapuhelin);
    }

    private Rooli mapRooliRows(Object[] rooliObj) {
        Integer tunnus = ((BigDecimal) rooliObj[0]).intValue();
        String nimi = (String) rooliObj[1];
        return new Rooli(tunnus, nimi);
    }
    public Set<JoinHenkiloRooliTable> getAllHenkiloRooliBySysteemiId(
            Integer systeemiId, Class<? extends JoinHenkiloRooliTable> criteriaClass, String propertyName, Set<PersonRole> includeRoles) {
        Set<Integer> includeRoleIDs = new HashSet<>(PersonRole.toIdList(new ArrayList<>(includeRoles)));
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(criteriaClass);
        criteria.add(Restrictions.eq(propertyName, systeemiId));
        criteria.add(Restrictions.in("rooliId", includeRoleIDs));
        List<JoinHenkiloRooliTable> list = (List<JoinHenkiloRooliTable>) criteria.list();
        closeSession();
        return new HashSet<>(list);
    }
}
