package fi.liike.rest.api.dto;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExternalSovellusCSVDto implements Serializable {
    private final Logger LOG = LoggerFactory.getLogger(ExternalSovellusCSVDto.class);

    private String adGroup;
    private String tier1;
    private String tier2;
    private String tier3;
    private String model;
    private String configVersion;
    private String configurationBasicNumber;
    private String criticality;
    private String manufacturer;
    private String version;
    private String personRole;
    private String signature0;
    private String misc;
    private String language;
    private String forOS;
    private String forArchitecture;
    private String platform;
    private String dependencies;
    private String relations;
    private String standardizedDate;
    private String acceptanceDate;
    private String company;
    private String full_Name;
    private String login_Name;
    private String peopleGroup_Form_Entry_ID;

    public static final String ACCEPTED_BY = "AcceptedBy"; //Tuotantoon hyväksynyt
    public static final String INSTALLATION_APPROVAL_NAME = "InstallationApprovalName"; //Asennuksen hyväksyjä
    private static final String MAIN_USER = "MainUser";
    private static final String OWNER = "Owner";

    private static final List<String> ALL_SOVHA_ROLE_NAMES = ImmutableList.of(ACCEPTED_BY, INSTALLATION_APPROVAL_NAME, MAIN_USER, OWNER);

    private List<String> acceptedBy = new ArrayList<>();
    private List<String> installationApprovalName = new ArrayList<>();

    public static final CsvSchema csvSchema = CsvSchema.builder()
            .addColumn("adGroup")
            .addColumn("tier1")
            .addColumn("tier2")
            .addColumn("tier3")
            .addColumn("model")
            .addColumn("configVersion")
            .addColumn("configurationBasicNumber")
            .addColumn("criticality")
            .addColumn("manufacturer")
            .addColumn("version")
            .addColumn("personRole")
            .addColumn("signature0")
            .addColumn("misc")
            .addColumn("language")
            .addColumn("forOS")
            .addColumn("forArchitecture")
            .addColumn("platform")
            .addColumn("dependencies")
            .addColumn("relations")
            .addColumn("standardizedDate")
            .addColumn("acceptanceDate")
            .addColumn("company")
            .addColumn("full_Name")
            .addColumn("login_Name")
            .addColumn("peopleGroup_Form_Entry_ID")
            .build();

    public ExternalSovellusCSVDto() {}

    public ExternalSovellusCSVDto(String adgroup, String signature, String versio, String personRole, String loginName) {
        this.adGroup = adgroup;
        this.signature0 = signature;
        this.version = versio;
        this.personRole = personRole;
        this.login_Name = loginName;
    }

    public ExternalSovellusCSVDto(String adGroup, String tier1, String tier2, String tier3, String model, String configVersion, String configurationBasicNumber, String criticality, String manufacturer, String version, String personRole, String signature0, String misc, String language, String forOS, String forArchitecture, String platform, String dependencies, String relations, String standardizedDate, String acceptanceDate, String company, String full_Name, String login_Name, String peopleGroup_Form_Entry_ID) {
        this.adGroup = adGroup;
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.model = model;
        this.configVersion = configVersion;
        this.configurationBasicNumber = configurationBasicNumber;
        this.criticality = criticality;
        this.manufacturer = manufacturer;
        this.version = version;
        this.personRole = personRole;
        this.signature0 = signature0;
        this.misc = misc;
        this.language = language;
        this.forOS = forOS;
        this.forArchitecture = forArchitecture;
        this.platform = platform;
        this.dependencies = dependencies;
        this.relations = relations;
        this.standardizedDate = standardizedDate;
        this.acceptanceDate = acceptanceDate;
        this.company = company;
        this.full_Name = full_Name;
        this.login_Name = login_Name;
        this.peopleGroup_Form_Entry_ID = peopleGroup_Form_Entry_ID;
    }

    public String getAdGroup() {
        return adGroup;
    }

    public void setAdGroup(String adGroup) {
        this.adGroup = adGroup;
    }

    public String getTier1() {
        return tier1;
    }

    public void setTier1(String tier1) {
        this.tier1 = tier1;
    }

    public String getTier2() {
        return tier2;
    }

    public void setTier2(String tier2) {
        this.tier2 = tier2;
    }

    public String getTier3() {
        return tier3;
    }

    public void setTier3(String tier3) {
        this.tier3 = tier3;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getConfigVersion() {
        return configVersion;
    }

    public void setConfigVersion(String configVersion) {
        this.configVersion = configVersion;
    }

    public String getConfigurationBasicNumber() {
        return configurationBasicNumber;
    }

    public void setConfigurationBasicNumber(String configurationBasicNumber) {
        this.configurationBasicNumber = configurationBasicNumber;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    public String getSignature0() {
        return signature0;
    }

    public void setSignature0(String signature0) {
        this.signature0 = signature0;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getForOS() {
        return forOS;
    }

    public void setForOS(String forOS) {
        this.forOS = forOS;
    }

    public String getForArchitecture() {
        return forArchitecture;
    }

    public void setForArchitecture(String forArchitecture) {
        this.forArchitecture = forArchitecture;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public String getRelations() {
        return relations;
    }

    public void setRelations(String relations) {
        this.relations = relations;
    }

    public String getStandardizedDate() {
        return standardizedDate;
    }

    public void setStandardizedDate(String standardizedDate) {
        this.standardizedDate = standardizedDate;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public String getLogin_Name() {
        return login_Name;
    }

    public void setLogin_Name(String login_Name) {
        this.login_Name = login_Name;
    }

    public String getPeopleGroup_Form_Entry_ID() {
        return peopleGroup_Form_Entry_ID;
    }

    public void setPeopleGroup_Form_Entry_ID(String peopleGroup_Form_Entry_ID) {
        this.peopleGroup_Form_Entry_ID = peopleGroup_Form_Entry_ID;
    }

    public List<String> getAcceptedBy() {
        return acceptedBy;
    }

    public List<String> getInstallationApprovalName() {
        return installationApprovalName;
    }

    public ExternalSovellusCSVDto of(List<ExternalSovellusCSVDto> sovellusList) {
        for (ExternalSovellusCSVDto sovellus : sovellusList) {
            String roleName = sovellus.getPersonRole();
            String personValue = parseLirsToLoginName(sovellus.getLogin_Name());

            if (personValue == null)  continue;

            if (roleName.equals(ACCEPTED_BY)) {
                acceptedBy.add(personValue);
            } else if (roleName.equals(INSTALLATION_APPROVAL_NAME)) {
                installationApprovalName.add(personValue);
            } else {
                if (!ALL_SOVHA_ROLE_NAMES.contains(roleName)) {
                    LOG.error("Unknown rolename {}", roleName);
                }
            }
        }
        return this;
    }

    String parseLirsToLoginName(String lirs) {
        if (lirs.startsWith("lirs.L")) {
            return lirs.replaceFirst("lirs.", "");
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ExternalSovellusCSVDto{" +
                "adGroup='" + adGroup + '\'' +
                ", tier1='" + tier1 + '\'' +
                ", tier2='" + tier2 + '\'' +
                ", tier3='" + tier3 + '\'' +
                ", model='" + model + '\'' +
                ", configVersion='" + configVersion + '\'' +
                ", configurationBasicNumber='" + configurationBasicNumber + '\'' +
                ", criticality='" + criticality + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", version='" + version + '\'' +
                ", personRole='" + personRole + '\'' +
                ", signature0='" + signature0 + '\'' +
                ", misc='" + misc + '\'' +
                ", language='" + language + '\'' +
                ", forOS='" + forOS + '\'' +
                ", forArchitecture='" + forArchitecture + '\'' +
                ", platform='" + platform + '\'' +
                ", dependencies='" + dependencies + '\'' +
                ", relations='" + relations + '\'' +
                ", standardizedDate='" + standardizedDate + '\'' +
                ", acceptanceDate='" + acceptanceDate + '\'' +
                ", company='" + company + '\'' +
                ", full_Name='" + full_Name + '\'' +
                ", login_Name='" + login_Name + '\'' +
                ", peopleGroup_Form_Entry_ID='" + peopleGroup_Form_Entry_ID + '\'' +
                ", acceptedBy=" + acceptedBy +
                ", installationApprovalName=" + installationApprovalName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExternalSovellusCSVDto that = (ExternalSovellusCSVDto) o;

        if (!adGroup.equals(that.adGroup)) return false;
        if (!version.equals(that.version)) return false;
        return signature0.equals(that.signature0);
    }

    @Override
    public int hashCode() {
        int result = adGroup.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + signature0.hashCode();
        return result;
    }
}
