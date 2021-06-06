package fi.liike.rest.api.dto;

import fi.liike.rest.api.ContentDto;
import fi.liike.rest.auth.Right;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class ContentDtoWithRights extends ContentDto implements RightsDto {
    private List<String> noRightsToModify;

    @Override
    public List<String> getNoRightsToModify() {
        return noRightsToModify;
    }

    @Override
    @JsonIgnore
    public void setNoRightsToModify(Set<Right> userRights) {
        this.noRightsToModify = new ArrayList<>();
        if (!userRights.contains(Right.MODIFY_UNSECURED_FIELDS))
            this.noRightsToModify.add("ALL_FIELDS");
    }
}
