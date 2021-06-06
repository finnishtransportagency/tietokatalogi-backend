package fi.liike.rest.api.dto;

import fi.liike.rest.api.ContentDto;
import fi.liike.rest.auth.Right;

import java.util.List;

public class JarjestelmaSahkeDto extends ContentDto {

    private Integer tunnus;
    private String nimi;
    private String paasynhallinta;
    private Boolean tietoturvasopimus;
    private String jarjestelmavastaavaId;
    private String omistajaId;
    private List<String> jarjestelmaSijaistenIdt;
    private String muokattuPvm;

    public Integer getTunnus() {
        return tunnus;
    }

    public void setTunnus(Integer tunnus) {
        this.tunnus = tunnus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getPaasynhallinta() {
        return paasynhallinta;
    }

    public void setPaasynhallinta(String paasynhallinta) {
        this.paasynhallinta = paasynhallinta;
    }

    public Boolean getTietoturvasopimus() {
        return tietoturvasopimus;
    }

    public void setTietoturvasopimus(Boolean tietoturvasopimus) {
        this.tietoturvasopimus = tietoturvasopimus;
    }

    public String getJarjestelmavastaavaId() {
        return jarjestelmavastaavaId;
    }

    public void setJarjestelmavastaavaId(String jarjestelmavastaavaId) {
        this.jarjestelmavastaavaId = jarjestelmavastaavaId;
    }

    public String getOmistajaId() {
        return omistajaId;
    }

    public void setOmistajaId(String omistajaId) {
        this.omistajaId = omistajaId;
    }

    public List<String> getJarjestelmaSijaistenIdt() {
        return jarjestelmaSijaistenIdt;
    }

    public void setJarjestelmaSijaistenIdt(List<String> jarjestelmaSijaistenIdt) {
        this.jarjestelmaSijaistenIdt = jarjestelmaSijaistenIdt;
    }

    public void setMuokattuPvm(String muokattuPvm) {
        this.muokattuPvm = muokattuPvm;
    }

    @Override
    public void setRivimuokkaajatunnus(String header) {

    }

    @Override
    public String getRivimuokkaajatunnus() {
        return null;
    }

    @Override
    public String toString() {
        return "JarjestelmaSahkeDto{" +
                "tunnus=" + tunnus +
                ", nimi='" + nimi + '\'' +
                ", paasynhallinta='" + paasynhallinta + '\'' +
                ", tietoturvasopimus=" + tietoturvasopimus +
                ", jarjestelmavastaavaId='" + jarjestelmavastaavaId + '\'' +
                ", omistajaId='" + omistajaId + '\'' +
                ", jarjestelmaSijaistenIdt=" + jarjestelmaSijaistenIdt +
                ", muokattuPvm='" + muokattuPvm + '\'' +
                '}';
    }
}
