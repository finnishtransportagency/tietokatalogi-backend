package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Palvelu;
import fi.liike.rest.Model.PalveluHistory;
import fi.liike.rest.Model.PalveluKasite;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class PalveluDaoImpl extends SearchDaoImpl implements MainDao {

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(Palvelu.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(Palvelu.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent, "palvelu_seq", new PalveluHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(null, content, new PalveluHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		return super.getResources(PalveluKasite.class);
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(Palvelu.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(Palvelu.class, PalveluHistory.class, id, new PalveluHistory(), deleteContent, remoteUser);
	}

}
