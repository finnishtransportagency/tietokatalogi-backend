package fi.liike.rest.api.dto;

public class FrontpageDto {

    private String mainText;
    private String sideText;

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getSideText() {
        return sideText;
    }

    public void setSideText(String sideText) {
        this.sideText = sideText;
    }

    @Override
    public String toString() {
        return "FrontpageDto{" +
                "mainText='" + mainText + '\'' +
                ", sideText='" + sideText + '\'' +
                '}';
    }
}
