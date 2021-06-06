package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietoryhma;
import fi.liike.rest.Model.TietoryhmaHistory;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class TietoryhmaDaoImpl extends SearchDaoImpl implements MainDao {

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(Tietoryhma.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(Tietoryhma.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent, "tietoryhma_seq", new TietoryhmaHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(session, content, new TietoryhmaHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		return null;
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(Tietoryhma.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(Tietoryhma.class, TietoryhmaHistory.class, id, new TietoryhmaHistory(),
				deleteContent, remoteUser);
	}
}
