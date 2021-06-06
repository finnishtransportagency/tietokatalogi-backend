package fi.liike.rest.api.dto;

import fi.liike.rest.auth.Right;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RightsDtoImpl implements RightsDto {

    private List<String> noRightsToModify;

    public List<String> getNoRightsToModify() {
        return noRightsToModify;
    }

    @JsonIgnore
    public void setNoRightsToModify(Set<Right> userRights) {
        this.noRightsToModify = new ArrayList<>();
        if (!userRights.contains(Right.MODIFY_UNSECURED_FIELDS))
            this.noRightsToModify.add("ALL_FIELDS");
    }
}
