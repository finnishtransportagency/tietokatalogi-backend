package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class JarjestelmaDaoImpl extends SearchDaoImpl implements MainDao {
	private final Logger LOG = LoggerFactory.getLogger(JarjestelmaDaoImpl.class);

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(Jarjestelma.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(Jarjestelma.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent, "jarjestelma_seq", new JarjestelmaHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(null, content, new JarjestelmaHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		List<KasiteArvoContent> resources = super.getResources(JarjestelmaKasite.class);
		resources.addAll(getClassResources(Sovellus.class, "Sovellus"));
		resources.addAll(getClassResources(Jarjestelma.class, "Järjestelmä"));

		return resources;
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(Jarjestelma.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(Jarjestelma.class, JarjestelmaHistory.class, id, new JarjestelmaHistory(),
				deleteContent, remoteUser);
	}

	public String getTietovirtaNimi(String tietovirta) {
		if (tietovirta == null) return "";
		try {
			getSession().beginTransaction();
			Criteria criteria = getSession().createCriteria(Tietolaji.class);
			criteria.add(Restrictions.eq("tunnus", Integer.parseInt(tietovirta)));
			Tietolaji result = (Tietolaji) criteria.uniqueResult();
			if (result != null)
				return result.getNimi();
			return "";
		}
		catch (NumberFormatException e) {
			LOG.error("Tietovirta should represent a number, got: " + tietovirta);
			return "";
		}
		finally {
			closeSession();
		}
	}

	public String getTietojarjestelmapalveluNimi(Integer tjpTunnus) {
		if (tjpTunnus == null) return "";
		getSession().beginTransaction();
		Criteria criteria = getSession().createCriteria(Tietojarjestelmapalvelu.class);
		criteria.add(Restrictions.eq("tunnus", tjpTunnus));
		Tietojarjestelmapalvelu result = (Tietojarjestelmapalvelu) criteria.uniqueResult();
		closeSession();
		return result.getNimi();
	}

	public List<JoinJarjestelmaLinkkaus> getJoinJarjestelmaLinkkausList(int tietojarjestelmaTunnus) {
		getSession().beginTransaction();
		Criteria criteria = getSession().createCriteria(JoinJarjestelmaLinkkaus.class);
		criteria.add(Restrictions.or(
				Restrictions.eq("parentNode", tietojarjestelmaTunnus),
				Restrictions.eq("childNode", tietojarjestelmaTunnus)
		));
		List<JoinJarjestelmaLinkkaus> result = criteria.list();
		closeSession();
		return result;
	}

}
