package fi.liike.rest.Model;

public abstract class JoinTietovarantoAttribute extends JoinTable {
    public abstract Integer getRivi_id();

    public abstract void setRivi_id(Integer rivi_id);

    public abstract String getAttribuuttiarvo();

    public abstract void setAttribuuttiarvo(String attribuuttiarvo);
}
