package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Paatietoryhma;
import fi.liike.rest.Model.PaatietoryhmaHistory;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class PaatietoryhmaDaoImpl extends SearchDaoImpl implements MainDao {

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(Paatietoryhma.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(Paatietoryhma.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent, "paatietoryhma_seq", new PaatietoryhmaHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(session, content, new PaatietoryhmaHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		return null;
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(Paatietoryhma.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(Paatietoryhma.class, PaatietoryhmaHistory.class, id, new PaatietoryhmaHistory(),
				deleteContent, remoteUser);
	}
}
