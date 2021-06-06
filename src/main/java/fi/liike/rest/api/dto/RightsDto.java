package fi.liike.rest.api.dto;

import fi.liike.rest.auth.Right;

import java.util.List;
import java.util.Set;

public interface RightsDto {
    public List<String> getNoRightsToModify();

    public void setNoRightsToModify(Set<Right> userRights);
}
