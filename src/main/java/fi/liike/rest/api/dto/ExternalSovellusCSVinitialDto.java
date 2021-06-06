package fi.liike.rest.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ExternalSovellusCSVinitialDto extends ExternalSovellusCSVDto {

    public static final String MAIN_USER = "MainUser";
    public static final String OWNER = "Owner";

    private List<String> mainUsers = new ArrayList<>();
    private List<String> owners = new ArrayList<>();

    public List<String> getMainUsers() {
        return mainUsers;
    }

    public List<String> getOwners() {
        return owners;
    }

    public ExternalSovellusCSVDto of(List<ExternalSovellusCSVDto> sovellusList) {
        for (ExternalSovellusCSVDto sovellus : sovellusList) {
            String roleName = sovellus.getPersonRole();
            String personValue = parseLirsToLoginName(sovellus.getLogin_Name());

            if (personValue == null)  continue;
            if (roleName.equals(MAIN_USER)) {
                mainUsers.add(personValue);
            } else if (roleName.equals(OWNER)) {
                owners.add(personValue);
            }
        }
        return super.of(sovellusList);
    }

    @Override
    public String toString() {
        return super.toString() +  ", mainUsers=" + mainUsers +
                ", owners=" + owners;
    }

}
