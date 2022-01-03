package fi.liike.dao;

import fi.liike.rest.Dao.HibernateDao;
import fi.liike.rest.Dao.HibernateSession;
import fi.liike.rest.Model.SovellusTemp;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SQLmergeTest extends HibernateSession {

    private final Logger LOG = LoggerFactory.getLogger(SQLmergeTest.class);
    private HibernateDao hibernateDao;
    private static String testTableName = "TEST_TABLE";
    private static String testTempTableName = "TEST_TABLE_TEMP";
    private static List<String> testTableRows = Arrays.asList(
            "ID NUMERIC NOT NULL UNIQUE",
            "OBJECT_ID CHARACTER VARYING(255) NOT NULL UNIQUE",
            "NAME CHARACTER VARYING(255) NOT NULL",
            "POISTUNUT NUMERIC DEFAULT 0"
    );

    @Before
    public void setUp() {
        hibernateDao = new HibernateDao();
        createTable(testTableName, testTableRows);
        createTable(testTempTableName, testTableRows);
    }

    @After
    public void tearDown() {
        dropTable(testTableName);
        dropTable(testTempTableName);
    }

    @Test
    public void getMergeTablesSqlTest() {
        String targetTable = "TARGET_TABLE";
        String sourceTable = "SOURCE_TABLE";
        List<String> matchingColumns = Collections.singletonList("OBJECT_ID");
        List<String> updateColumns = Arrays.asList("NAME", "INACTIVE");
        List<String> insertColumns = Arrays.asList("ID", "OBJECT_ID", "NAME", "INACTIVE");

        String sql = hibernateDao.getMergeTablesSql(targetTable, "seq_" + targetTable, "ID",
                sourceTable, matchingColumns, updateColumns, insertColumns);

        String expectedSql = "INSERT INTO TARGET_TABLE (id,object_id,name,inactive)\n" +
                "SELECT nextval('seq_TARGET_TABLE'),src.object_id,src.name,src.inactive FROM SOURCE_TABLE src\n" +
                "ON CONFLICT (object_id)\n" +
                "DO UPDATE\n" +
                "SET name = excluded.name,\n" +
                "inactive = excluded.inactive\n";
        assertThat(expectedSql, is(sql));
    }

    @Test
    @Ignore
    // requires pgsql
    public void mergeTablesTest() {
        //Notice: IDs are only expected values

        List<SovellusTemp.TestObj> initObjList = Arrays.asList(
                getTestObj("a", "a"),
                getTestObj("b", "b"),
                getTestObj("y", "y")
        );

        //Initialize table
        initializeTable(initObjList);

        List<SovellusTemp.TestObjTemp> newTestObjList = Arrays.asList(
                getTestObjTemp("a", "aX"),
                getTestObjTemp("b", "b"),
                getTestObjTemp("c", "c"));


        List<Object> newObjList = new ArrayList<>();
        newObjList.addAll(newTestObjList);

        List<String> updateColumns = Collections.singletonList("NAME");
        List<String> insertColumns = Arrays.asList("ID", "OBJECT_ID", "NAME");
        String tableIdSequence = "seq_" + testTableName;
        String idColumn = "ID";
        List<String> matchingColumns = Collections.singletonList("OBJECT_ID");

        hibernateDao.saveTempTableAndMergeTables(newObjList, testTempTableName, testTableName, tableIdSequence,
                idColumn, matchingColumns, "POISTUNUT", 1, updateColumns, insertColumns);

        List<SovellusTemp.TestObj> fetchedList = getTestObjList(SovellusTemp.TestObj.class);
        SovellusTemp.TestObj expectedObj1 = getTestObj("a", "aX", 0);
        SovellusTemp.TestObj expectedObj2 = getTestObj("b", "b", 0);
        SovellusTemp.TestObj expectedObj3 = getTestObj("y", "y", 1);
        SovellusTemp.TestObj expectedObj4 = getTestObj("c", "c", 0);
        List<SovellusTemp.TestObj> expectedTargetList = Arrays.asList(expectedObj1, expectedObj2, expectedObj3, expectedObj4);
        assertTestObjList(expectedTargetList, fetchedList);

        newObjList.add(getTestObjTemp("y", "y"));
        hibernateDao.saveTempTableAndMergeTables(newObjList, testTempTableName, testTableName, tableIdSequence,
                idColumn, matchingColumns, "POISTUNUT", 1, updateColumns, insertColumns);
        fetchedList = getTestObjList(SovellusTemp.TestObj.class);
        expectedObj3 = getTestObj("y", "y", 0);
        expectedTargetList = Arrays.asList(expectedObj1, expectedObj2, expectedObj3, expectedObj4);
        assertTestObjList(expectedTargetList, fetchedList);
    }

    private void assertTestObjList(List<SovellusTemp.TestObj> expectedList, List<SovellusTemp.TestObj> fetchedList) {
        // Sort lists alphabetically by name so that we can iterate over both lists in the same order
        Comparator<SovellusTemp.TestObj> nameComparator = new Comparator<SovellusTemp.TestObj>() {
            @Override
            public int compare(SovellusTemp.TestObj left, SovellusTemp.TestObj right) {
                return left.getName().compareTo(right.getName());
            }
        };
        Collections.sort(expectedList, nameComparator);
        Collections.sort(fetchedList, nameComparator);

        SovellusTemp.TestObj expected;
        SovellusTemp.TestObj fetched;
        for (int i = 0; i < expectedList.size(); i++) {
            expected = expectedList.get(i);
            fetched = fetchedList.get(i);
            assertThat(expected.getObjectID(), is(fetched.getObjectID()));
            assertThat(expected.getName(), is(fetched.getName()));
            assertThat(expected.getInactive(), is(fetched.getInactive()));
        }
    }

    private SovellusTemp.TestObjTemp getTestObjTemp(String objectId, String name) {
        SovellusTemp.TestObjTemp testObj = new SovellusTemp.TestObjTemp();
        testObj.setName(name);
        testObj.setObjectID(objectId);
        return testObj;
    }

    private SovellusTemp.TestObj getTestObj(String objectId, String name) {
        return getTestObj(objectId, name, 0);
    }

    private SovellusTemp.TestObj getTestObj(String objectId, String name, Integer inactive) {
        SovellusTemp.TestObj testObj = new SovellusTemp.TestObj();
        testObj.setName(name);
        testObj.setObjectID(objectId);
        testObj.setInactive(inactive);
        return testObj;
    }

    private List<SovellusTemp.TestObj> getTestObjList(Class className) {
        Transaction trx = getSession().beginTransaction();
        Criteria crit = getSession().createCriteria(className);
        List<SovellusTemp.TestObj> testObjList = crit.list();
        trx.commit();
        closeSession();
        return testObjList;
    }

    private void initializeTable(List<SovellusTemp.TestObj> testObjList) {
        for (SovellusTemp.TestObj testObj : testObjList) {
            Transaction transaction = getSession().beginTransaction();
            getSession().save(testObj);
            transaction.commit();
            getSession().close();
        }
    }

    private void executeBasicSqlUpdate(String sql) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            hibernateDao.executeBasicSqlUpdate(sql, session);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                LOG.error("There was an error: " + e.getMessage());
                transaction.rollback();
            } catch (RuntimeException ex) {
                LOG.error("Cannot roll back transaction", ex);
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    private void createTable(String tableName, List<String> createRows) {
        StringBuilder sql = new StringBuilder(format("CREATE TABLE %s (\n", tableName));
        for (String createRow : createRows) {
            sql.append(createRow + ",\n");
        }
        sql.replace(sql.length() - 2, sql.length(), ")");
        executeBasicSqlUpdate(sql.toString());

        String sequenceSql = format("CREATE SEQUENCE seq_%s start with 1 increment by 1", tableName);
        executeBasicSqlUpdate(sequenceSql.toString());

    }

    private void dropTable(String tableName) {
        String sql = format("DROP TABLE IF EXISTS %s;", tableName);
        executeBasicSqlUpdate(sql);
        String sequenceSql = format("DROP SEQUENCE seq_%s", tableName);
        executeBasicSqlUpdate(sequenceSql);
    }
}

