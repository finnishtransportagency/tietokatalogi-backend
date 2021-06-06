package fi.liike.rest.Model.Dto;

import java.util.List;

import fi.liike.rest.Model.linkitys.LinkitysHierarkia;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.collect.Lists;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.MolekyyliHierarkia;


public class MolekyyliLinkkiDto extends Haettava{

    private List<MolekyyliKasite> nodes = Lists.newArrayList();

    private List<MolekyyliLinkitys> links = Lists.newArrayList();

    public void lisaa(LinkitysHierarkia h) {

        if (h == null){
            return;
        }
        String lahdeNimi = h.getLahdeNimi();
        String kohdeNimi = h.getKohdeNimi();
        String lahdeKasite = h.getLahdeTaulu();
        String kohdeKasite = h.getKohdeTaulu();
        String lahdeTunnus = h.getLahdeTunnus();
        String kohdeTunnus = h.getKohdeTunnus();

        MolekyyliKasite lahde = new MolekyyliKasite(lahdeNimi, lahdeKasite, Integer.valueOf(lahdeTunnus));
        MolekyyliKasite kohde = new MolekyyliKasite(kohdeNimi, kohdeKasite, Integer.valueOf(kohdeTunnus));
        int lahdeI = addedAlready(lahde);
        int kohdeI = addedAlready(kohde);

        if (lahdeI == -1){
            nodes.add(lahde);
            lahdeI = nodes.indexOf(lahde);
        }
        if (kohdeI == -1){
            nodes.add(kohde);
            kohdeI = nodes.indexOf(kohde);
        }

        MolekyyliLinkitys linkki = new MolekyyliLinkitys(lahdeI, kohdeI);
        links.add(linkki);
    }

    private int addedAlready(MolekyyliKasite kasite) {

        int alreadyAdded = -1;
        for (MolekyyliKasite k : nodes){
            if (k.getHash().equals(kasite.getHash())){
                alreadyAdded = nodes.indexOf(k);
            }
        }

        return alreadyAdded;
    }

    public List<MolekyyliKasite> getNodes() {
        return nodes;
    }

    public void setNodes(List<MolekyyliKasite> nodes) {
        this.nodes = nodes;
    }

    public List<MolekyyliLinkitys> getLinks() {
        return links;
    }

    public void setLinks(List<MolekyyliLinkitys> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MolekyyliLinkkiDto that = (MolekyyliLinkkiDto) o;

        if (nodes != null ? !nodes.equals(that.nodes) : that.nodes != null) return false;
        return links != null ? links.equals(that.links) : that.links == null;
    }

    @Override
    public int hashCode() {
        int result = nodes != null ? nodes.hashCode() : 0;
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("MolekyyliLinkkiDto[Nodes = %s, Links = %s]", this.nodes.toString(), this.links.toString());
    }

    public static class MolekyyliKasite {

        public MolekyyliKasite(String nimi, String kasite, int code){
            this.name = nimi;
            this.content = kasite;
            this.code = code;
        }

        @JsonIgnore
        public String getHash() {
            return this.name + "." + this.content + "." + this.code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MolekyyliKasite that = (MolekyyliKasite) o;

            if (code != that.code) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return content != null ? content.equals(that.content) : that.content == null;
        }

        @Override
        public String toString() {
            return "MolekyyliKasite{" +
                    "code=" + code +
                    ", name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        @Override
        public int hashCode() {
            int result = code;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (content != null ? content.hashCode() : 0);
            return result;
        }

        private int code;

        private String name;

        private String content;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String kasite) {
            this.content = kasite;
        }

    }

    public static class MolekyyliLinkitys {

        public MolekyyliLinkitys(long s, long t){
            this.source = s;
            this.target = t;
        }

        private long source;

        private long target;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MolekyyliLinkitys that = (MolekyyliLinkitys) o;

            if (source != that.source) return false;
            return target == that.target;
        }

        @Override
        public int hashCode() {
            int result = (int) (source ^ (source >>> 32));
            result = 31 * result + (int) (target ^ (target >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "MolekyyliLinkitys{" +
                    "source=" + source +
                    ", target=" + target +
                    '}';
        }

        public long getSource() {
            return source;
        }

        public void setSource(long source) {
            this.source = source;
        }

        public long getTarget() {
            return target;
        }

        public void setTarget(long target) {
            this.target = target;
        }
    }
}
