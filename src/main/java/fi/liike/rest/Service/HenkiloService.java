package fi.liike.rest.Service;

import com.google.common.collect.ImmutableSet;
import fi.liike.config.Configurations;
import fi.liike.rest.Dao.Hibernate.HenkiloDao;
import fi.liike.rest.Dao.HibernateSession;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.Converter.HenkiloConverter;
import fi.liike.rest.api.dto.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static java.lang.String.format;

public class HenkiloService extends HibernateSession {
    private final HenkiloConverter henkiloConverter;
    private final HenkiloDao henkiloDao;
    private final Logger LOG = LoggerFactory.getLogger(HenkiloService.class);

    public HenkiloService() {
        this.henkiloConverter = new HenkiloConverter();
        this.henkiloDao = new HenkiloDao();
        Configurations.readConfigurations();
    }

    public HenkiloService(HenkiloDao henkiloDao) {
        this.henkiloConverter = new HenkiloConverter();
        this.henkiloDao = henkiloDao;
        Configurations.readConfigurations();
    }

    public Henkilo getHenkiloByLoginName(String loginName) {
        return henkiloDao.getHenkiloByKayttajatunnus(loginName);
    }

    public List<HenkiloDto> getHenkiloList(String filterProperty, String filter) {
        List<Henkilo> henkiloList = henkiloDao.getHenkiloList(filterProperty, filter, false);
        return henkiloConverter.henkiloModelListToDtoList(henkiloList);
    }

    public Set<FetchHenkiloRooliDto> getFetchHenkiloRooliListBySystemId(
            Integer systemId, Boolean filterDisabled, Class<? extends JoinHenkiloRooliTable> joinClass,
            String joinTableIdColumn) {
        Set<Integer> systemIds = new HashSet<>();
        systemIds.add(systemId);
        return getFetchHenkiloRooliListBySystemIds(systemIds, filterDisabled, joinClass, joinTableIdColumn).get(systemId);
    }

    Map<Integer, Set<FetchHenkiloRooliDto>> getFetchHenkiloRooliListBySystemIds(
            Set<Integer> systemIds, Boolean filterDisabled, Class<? extends JoinHenkiloRooliTable> joinClass,
            String joinTableIdColumn) {
        Map<Integer, Set<Rooli>> rooliSetSystemIdMap = getSystemIdRooliSetMap(systemIds, joinClass, joinTableIdColumn);

        return henkiloDao.getConvertedFetchHenkRooliSetSystemIdMap(rooliSetSystemIdMap, filterDisabled, joinClass,
                joinTableIdColumn, henkiloConverter);
    }

    private Map<Integer, Set<Rooli>> getSystemIdRooliSetMap(Set<Integer> systemIds, Class<? extends JoinHenkiloRooliTable> joinClass,
                                                            String jointableIdColumn) {
        return henkiloDao.getRooliSetSystemIdMap(systemIds, joinClass, jointableIdColumn);
    }

    Set<JoinHenkiloRooliTable> getHenkiloRooliListBySysteemiId(
            Integer systeemiId, Class<? extends JoinHenkiloRooliTable> criteriaClass, String propertyName) {
        return getHenkiloRooliListBySysteemiId(systeemiId, criteriaClass, propertyName, ImmutableSet.copyOf(PersonRole.values()));
    }

    private Set<JoinHenkiloRooliTable> getHenkiloRooliListBySysteemiId(
            Integer systeemiId, Class<? extends JoinHenkiloRooliTable> criteriaClass, String propertyName, Set<PersonRole> includeRoles) {
        Set<JoinHenkiloRooliTable> henkiloRooliSet = henkiloDao.getAllHenkiloRooliBySysteemiId(systeemiId, criteriaClass, propertyName, includeRoles);
        LOG.debug(format("Current henkiloRooliList: %s", henkiloRooliSet));
        return henkiloRooliSet;
    }

    private Map<Integer, List<Integer>> getCurrentRooliIdToHenkiloIdList(Set<? extends JoinHenkiloRooliTable> currList) {
        Map<Integer, List<Integer>> currMapRooliIdToHenkiloIdList = new HashMap<>();
        for (JoinHenkiloRooliTable henkiloRooli : currList) {
            Integer rooliId = henkiloRooli.getRooliId();
            if (!currMapRooliIdToHenkiloIdList.containsKey(rooliId)) {
                currMapRooliIdToHenkiloIdList.put(rooliId, new ArrayList<Integer>());
            }
            currMapRooliIdToHenkiloIdList.get(rooliId).add(henkiloRooli.getHenkiloId());
        }
        return currMapRooliIdToHenkiloIdList;
    }

    private Map<Integer, List<Integer>> getPassedRooliIdToHenkiloIdList(Set<HenkiloRooliDto> passedList) {
        Map<Integer, List<Integer>> mapRooliIdToHenkiloRooliList = new HashMap<>();
        for (HenkiloRooliDto henkiloRooli : passedList) {
            Integer rooliId = henkiloRooli.getRooliId();
            if (!mapRooliIdToHenkiloRooliList.containsKey(rooliId)) {
                mapRooliIdToHenkiloRooliList.put(rooliId, new ArrayList<Integer>());
            }
            mapRooliIdToHenkiloRooliList.get(rooliId).add(henkiloRooli.getHenkiloId());
        }

        return mapRooliIdToHenkiloRooliList;
    }

    private void validateHenkiloRooliList(Set<HenkiloRooliDto> passedList, Set<PersonRole> includeRoles) throws IOException {
        Boolean isInvalid = false;
        Map<Integer, List<Integer>> mapRoleIdToPersonIdList = new HashMap<>();
        for (HenkiloRooliDto henkiloRooli : ImmutableSet.copyOf(passedList)) {
            Integer roleId = henkiloRooli.getRooliId();
            if (!includeRoles.contains(PersonRole.toPersonRole(roleId))) {
                passedList.remove(henkiloRooli);
                continue;
            }

            Integer personId = henkiloRooli.getHenkiloId();
            if (!mapRoleIdToPersonIdList.containsKey(roleId)) {
                mapRoleIdToPersonIdList.put(roleId, new ArrayList<Integer>());
            }
            mapRoleIdToPersonIdList.get(roleId).add(personId);
        }

        Integer roleVastaavaId = PersonRole.VASTAAVA.getId();
        Integer roleSijainenId = PersonRole.SIJAINEN.getId();
        List<Integer> vastaavaPersonList = mapRoleIdToPersonIdList.get(roleVastaavaId);
        List<Integer> sijainenPersonList = mapRoleIdToPersonIdList.get(roleSijainenId);
        if (vastaavaPersonList != null && vastaavaPersonList.size() > 0
                && sijainenPersonList != null && sijainenPersonList.size() > 0) {
            for (Integer vastaavaPersonId : vastaavaPersonList) {
                if (sijainenPersonList.contains(vastaavaPersonId)) {
                    isInvalid = true;
                }
            }
        }
        if (isInvalid) {
            throw new IOException("Same person can not be vastaava and sijainen on the same system");
        }
    }

    public void addAndDeleteSystemHenkiloRoolis(
            Class<? extends JoinHenkiloRooliTable> joinClass,
            Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass, List<HenkiloRooliDto> passedList,
            Integer systeemiId, String rivimuokkaajatunnus, String criterionPropertyName) throws SQLException, IOException {
        addAndDeleteSystemHenkiloRoolis(joinClass, joinHistoryClass, passedList, systeemiId, rivimuokkaajatunnus,
                criterionPropertyName, ImmutableSet.copyOf(PersonRole.values()));
    }

    public void addAndDeleteSystemHenkiloRoolis(
            Class<? extends JoinHenkiloRooliTable> joinClass,
            Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass, List<HenkiloRooliDto> passedList,
            Integer systeemiId, String rivimuokkaajatunnus, String criterionPropertyName, Set<PersonRole> includeRoles) throws IOException, SQLException {
        if (passedList == null) return;
        Set<HenkiloRooliDto> passedSet = new HashSet<>(passedList);
        validateHenkiloRooliList(passedSet, includeRoles);

        Set<JoinHenkiloRooliTable> currSet = getHenkiloRooliListBySysteemiId(systeemiId, joinClass, criterionPropertyName, includeRoles);
        LOG.info("Modify {}. SysteemiId: {}, request list: {}. Current: {}", joinClass, systeemiId, passedSet, currSet);

        //This maps current roles (ids) to persons (ids)
        Map<Integer, List<Integer>> currentRooliHenkiloListMap = getCurrentRooliIdToHenkiloIdList(currSet);
        Map<Integer, List<Integer>> passedRooliHenkiloListMap = getPassedRooliIdToHenkiloIdList(passedSet);

        Set<Integer> roles = ImmutableSet.<Integer>builder()
                .addAll(currentRooliHenkiloListMap.keySet())
                .addAll(passedRooliHenkiloListMap.keySet())
                .build();

        Set<JoinHenkiloRooliTable> addList = new HashSet<>();
        Set<JoinHenkiloRooliTable> deleteList = new HashSet<>();

        if (!passedSet.isEmpty()) {
            for (Integer rooli : roles) {
                populateDeleteAndAddLists(currentRooliHenkiloListMap.get(rooli), passedRooliHenkiloListMap.get(rooli),
                        addList, deleteList, rooli, systeemiId, joinClass, rivimuokkaajatunnus);
            }
        } else {
            deleteList = currSet;
        }

        saveAndDeleteHenkiloLists(addList, deleteList, joinHistoryClass);
    }

    private void saveAndDeleteHenkiloLists(Set<JoinHenkiloRooliTable> addList, Set<JoinHenkiloRooliTable> deleteList, Class<? extends JoinHenkiloRooliTableHistory> joinHistoryClass) throws SQLException {
        Session session = getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            LOG.info("About to save HenkiloRooli list {} and delete {}", addList, deleteList);
            henkiloDao.saveHenkiloRooliList(session, addList, joinHistoryClass);
            henkiloDao.deleteHenkiloRooliList(session, deleteList, joinHistoryClass);
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                LOG.error("Unable to do a transaction. Error message: " + e.getMessage());
                transaction.rollback();
            } catch (RuntimeException ex) {
                LOG.error("Cannot roll back transaction! Error Message: " + ex.getMessage());
            }
        } finally {
            if (session != null) {
                closeSession();
            }
        }
    }

    private void populateDeleteAndAddLists(
            List<Integer> currHenkiloIdList, List<Integer> passedHenkiloIdList,
            Set<JoinHenkiloRooliTable> addList, Set<JoinHenkiloRooliTable> deleteList, Integer rooli, Integer systeemiId,
            Class<? extends JoinHenkiloRooliTable> joinClass, String rivimuokkaajatunnus) {
        Set<Integer> allHenkiloIDs = new HashSet<>();
        if (currHenkiloIdList != null) allHenkiloIDs.addAll(currHenkiloIdList);
        if (passedHenkiloIdList != null) allHenkiloIDs.addAll(passedHenkiloIdList);
        for (Integer henkiloId : allHenkiloIDs) {
            if (passedHenkiloIdList != null && passedHenkiloIdList.contains(henkiloId)) {
                if (currHenkiloIdList == null || !currHenkiloIdList.contains(henkiloId)) {
                    addList.add(convertToJoinHenkiloRooliTable(joinClass, henkiloId, rooli, systeemiId, rivimuokkaajatunnus));
                }
            } else {
                deleteList.add(convertToJoinHenkiloRooliTable(joinClass, henkiloId, rooli, systeemiId, rivimuokkaajatunnus));
            }
        }
    }

    private JoinHenkiloRooliTable convertToJoinHenkiloRooliTable
            (Class<? extends JoinHenkiloRooliTable> joinClass, Integer henkiloId, Integer rooliId,
             Integer systeemiId, String rivimuokkaajatunnus) {

        if (joinClass.equals(JarjestelmaHenkiloRooli.class)) {
            return new JarjestelmaHenkiloRooli(henkiloId, rooliId, systeemiId, rivimuokkaajatunnus);
        } else {
            return new SovellusHenkiloRooli(henkiloId, rooliId, systeemiId, rivimuokkaajatunnus);
        }
    }

    List<FetchHenkiloRooliDto> getSovellusHenkiloRooliList(Sovellus sovellus) {
        List<FetchHenkiloRooliDto> fetchHenkiloRooliList = new ArrayList<>(
                getFetchHenkiloRooliListBySystemId(sovellus.getTunnus(), false, SovellusHenkiloRooli.class,
                        "SOVELLUS_ID"));

        return fetchHenkiloRooliList;
    }

    //Only for testing
    public JoinHenkiloRooliTable saveHenkiloRooli(JoinHenkiloRooliTable content, JoinHenkiloRooliTableHistory history) throws SQLException {
        return henkiloDao.saveHenkiloRooli(null, content, history);
    }


    //Only for testing
    public List<Henkilo> getHenkiloByRooliAndSystemId(
            Integer rooliId, Integer systemId, Boolean filterDisabled,
            Class<? extends JoinHenkiloRooliTable> joinClass, String joinIdColumn) {
        return henkiloDao.getHenkiloListByRooliAndSystemId(rooliId, systemId, filterDisabled, joinClass, joinIdColumn);
    }

    //Only for testing
    public Rooli saveRooli(Rooli rooli) throws SQLException {
        return henkiloDao.saveRooli(rooli);
    }

    //Only for testing
    public List<HenkiloDto> getHenkiloListAll() {
        List<Henkilo> henkiloList = henkiloDao.getHenkiloList(null, null, true);
        return henkiloConverter.henkiloModelListToDtoList(henkiloList);
    }

    //Only for testing
    public List<Henkilo> saveHenkiloList(List<Henkilo> henkiloList) {
        return henkiloDao.saveHenkiloList(henkiloList);
    }

    //Only for testing
    public Set<Rooli> getRooliSetBySystemId(Integer systemId, Class<? extends JoinHenkiloRooliTable> joinClass,
                                            String jointableIdColumn) {
        Set<Integer> systemIds = new HashSet<>();
        systemIds.add(systemId);
        return getSystemIdRooliSetMap(systemIds, joinClass, jointableIdColumn).get(systemId);
    }
}
