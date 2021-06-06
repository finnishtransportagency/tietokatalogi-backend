package fi.liike.rest.Dao;

import java.util.List;
import java.util.Map;

import fi.liike.rest.Model.Hierarkia;
import fi.liike.rest.Model.JoinJarjestelmaLinkkaus;
import fi.liike.rest.Model.JoinTable;
import fi.liike.rest.Model.MolekyyliHierarkia;
import fi.liike.rest.Model.linkitys.LinkitysHierarkia;

public interface HierarkiaDao {

//    @Deprecated
//    public List<Hierarkia> haeHierarkiastaNimella(String nimi);

//    @Deprecated
//    public List<MolekyyliHierarkia> haeTyypilla(String tyyppiNimi);

    public List<LinkitysHierarkia> haeTunnuksella(String tunnus, Class<? extends JoinTable> joinTableClass,
                                                  Map<String, Object> propertyRestrictionMap,
                                                  MainDao childNodeDao, MainDao parentNodeDao);

    List<LinkitysHierarkia> haeJarjestelmalinkkiTunnuksella(String tunnus,
                                                            Map<String, Object> propertyRestrictionMap,
                                                            MainDao childNodeDao, MainDao parentNodeDao,
                                                            String childTableName, String parentTableName);

//    @Deprecated
//    public List<MolekyyliHierarkia> haeKaikki();

    public int poista(String id);

    public int paivita(String id, String kuvaus);

    public int lisaa(String id, int lahde, int kohde, String kuvaus);

}
