package fi.liike.rest.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum PersonRole {
    OMISTAJA(1),
    VASTAAVA(2),
    SIJAINEN(3),
    TUOTANTOON_HYVAKSYNYT(4),
    ASENNUKSEN_HYVAKSYNYT(5);
    private final Integer id;

    PersonRole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static PersonRole toPersonRole(Integer value) {
        switch (value) {
            case 1:
                return PersonRole.OMISTAJA;
            case 2:
                return PersonRole.VASTAAVA;
            case 3:
                return PersonRole.SIJAINEN;
            case 4:
                return PersonRole.TUOTANTOON_HYVAKSYNYT;
            case 5:
                return PersonRole.ASENNUKSEN_HYVAKSYNYT;
            default:
                return null;
        }
    }

    public static List<Integer> toIdList(List<PersonRole> personRoles) {
        List<Integer> idList = new ArrayList<>();
        for (PersonRole personRole : personRoles) {
            idList.add(personRole.getId());
        }
        return idList;
    }
}
