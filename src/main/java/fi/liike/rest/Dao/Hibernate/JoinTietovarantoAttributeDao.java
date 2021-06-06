package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.JoinDao;
import fi.liike.rest.Model.JoinTietovarantoAttribute;
import fi.liike.rest.Model.JoinTietovarantoHenkilotietoRyhma;
import fi.liike.rest.Model.JoinTietovarantoHenkilotietoRyhmaHistory;
import fi.liike.rest.Model.JoinTietovarantoKasittelynPeruste;
import fi.liike.rest.Model.JoinTietovarantoKasittelynPerusteHistory;
import fi.liike.rest.Model.JoinTietovarantoRekisteroityRyhma;
import fi.liike.rest.Model.JoinTietovarantoRekisteroityRyhmaHistory;
import fi.liike.rest.Model.JoinTietovarantoTiedonohjaus;
import fi.liike.rest.Model.JoinTietovarantoTiedonohjausHistory;
import fi.liike.rest.Model.JoinTietovarantoTurvatoimenpide;
import fi.liike.rest.Model.JoinTietovarantoTurvatoimenpideHistory;
import fi.liike.rest.Model.JoinTietovarantoVastaanottajaRyhma;
import fi.liike.rest.Model.JoinTietovarantoVastaanottajaRyhmaHistory;
import fi.liike.rest.Model.JoinTietovarantoYhteisrekisterinpitaja;
import fi.liike.rest.Model.JoinTietovarantoYhteisrekisterinpitajaHistory;
import fi.liike.rest.Model.JoinTietovarantoYllapitoMuuTaho;
import fi.liike.rest.Model.JoinTietovarantoYllapitoMuuTahoHistory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class JoinTietovarantoAttributeDao extends JoinMainDao implements JoinDao {
    private List<JoinTietovarantoAttribute> tietovarantoAttributeList;
    private Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass;

    @Override
    public JoinDao getDao(int parentNode, String remoteUser) {
        return null;
    }

    public JoinDao getDao(List<JoinTietovarantoAttribute> tietovarantoAttributeList,
                          Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass,
                          String remoteUser) {
        this.remoteUser = remoteUser;
        this.tietovarantoAttributeList = tietovarantoAttributeList;
        this.joinTietovarantoAttributeClass = joinTietovarantoAttributeClass;
        return this;
    }

    @Override
    public Integer getParentNodeId(int childNode) {
        // Not used
        return null;
    }

    @Override
    public void save(Session session, int childNode) {
        // In this case childNode is parentNode i.e tietovarantotunnus
        save(session, childNode, this.tietovarantoAttributeList);
    }

    private void save(Session session, Integer parentNode, List<JoinTietovarantoAttribute> tietovarantoAttributeList) {
        for (JoinTietovarantoAttribute tietovarantoAttribute : tietovarantoAttributeList) {
            if (parentNode != null) {
                tietovarantoAttribute.setParentNode(parentNode);
            }
            if (tietovarantoAttribute instanceof JoinTietovarantoYhteisrekisterinpitaja) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoYhteisrekisterinpitajaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoRekisteroityRyhma) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoRekisteroityRyhmaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoHenkilotietoRyhma) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoHenkilotietoRyhmaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoTurvatoimenpide) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoTurvatoimenpideHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoKasittelynPeruste) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoKasittelynPerusteHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoYllapitoMuuTaho) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoYllapitoMuuTahoHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoVastaanottajaRyhma) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoVastaanottajaRyhmaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoTiedonohjaus) {
                super.createEntry(session, tietovarantoAttribute,
                        new JoinTietovarantoTiedonohjausHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            }
        }
    }

    @Override
    public void update(Session session, int childNode) {
        // In this case childNode is parentNode i.e tietovarantotunnus
        // The specific class must be used here otherwise createCriteria may use an unintended table
        Criteria criteria = session.createCriteria(this.joinTietovarantoAttributeClass);
        criteria.add(Restrictions.eq("parentNode", childNode));

        List<JoinTietovarantoAttribute> existingList = criteria.list();

        UpdateChangeListContainer changeLists = getUpdateChangeLists(existingList, this.tietovarantoAttributeList);

        this.save(session, childNode, (List<JoinTietovarantoAttribute>) changeLists.getCreateList());
        this.delete(session, (List<JoinTietovarantoAttribute>) changeLists.getDeleteList());
    }

    @Override
    public void delete(Session session, int childNode) {
        delete(session, this.tietovarantoAttributeList);
    }

    private void delete(Session session, List<JoinTietovarantoAttribute> tietovarantoAttributeList) {
        for (JoinTietovarantoAttribute tietovarantoAttribute : tietovarantoAttributeList) {
            if (tietovarantoAttribute instanceof JoinTietovarantoYhteisrekisterinpitaja) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoYhteisrekisterinpitajaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoRekisteroityRyhma) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoRekisteroityRyhmaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoHenkilotietoRyhma) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoHenkilotietoRyhmaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoTurvatoimenpide) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoTurvatoimenpideHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoKasittelynPeruste) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoKasittelynPerusteHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoYllapitoMuuTaho) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoYllapitoMuuTahoHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoVastaanottajaRyhma) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoVastaanottajaRyhmaHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            } else if (tietovarantoAttribute instanceof JoinTietovarantoTiedonohjaus) {
                super.delete(session, tietovarantoAttribute,
                        new JoinTietovarantoTiedonohjausHistory(tietovarantoAttribute.getParentNode(),
                                tietovarantoAttribute.getAttribuuttiarvo()));
            }
        }
    }

    public List<String> getTietovarantoAttributeList(int tietovarantoId, Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(joinTietovarantoAttributeClass);
        criteria.add(Restrictions.eq("parentNode", tietovarantoId));
        List<JoinTietovarantoAttribute> results = criteria.list();
        closeSession();
        List<String> attributeValues = new ArrayList<>();
        for (JoinTietovarantoAttribute result : results) {
            attributeValues.add(result.getAttribuuttiarvo());
        }
        return attributeValues;
    }

    public List<String> getAllTietovarantoAttributes(Class<? extends JoinTietovarantoAttribute> joinTietovarantoAttributeClass) {
        getSession().beginTransaction();
        Criteria criteria = getSession().createCriteria(joinTietovarantoAttributeClass);
        List<JoinTietovarantoAttribute> results = criteria.list();
        closeSession();
        List<String> attributeValues = new ArrayList<>();
        for (JoinTietovarantoAttribute result : results) {
            attributeValues.add(result.getAttribuuttiarvo());
        }
        return attributeValues;
    }
}
