package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.*;
import fi.liike.rest.api.*;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LooginenDaoImpl extends SearchDaoImpl implements MainDao {

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(LooginenTietovaranto.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(LooginenTietovaranto.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent, "looginentv_seq", new LooginenTietovarantoHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(session, content, new LooginenTietovarantoHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		Criteria crit = getSession().createCriteria(LooginenKasite.class);
		List<LooginenKasite> resources = list(crit);
		closeSession();

		ArrayList<KasiteArvoContent> content = new ArrayList<>();
		for (LooginenKasite kasiteArvo : resources) {
			content.add(new AreaCodeKasiteArvoContent(kasiteArvo.getId(), kasiteArvo.getKasite(),
					kasiteArvo.getArvo(), kasiteArvo.getAreaCode()));
		}

		return content;
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(LooginenTietovaranto.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(LooginenTietovaranto.class, LooginenTietovarantoHistory.class, id,
				new LooginenTietovarantoHistory(), deleteContent, remoteUser);
	}
}
