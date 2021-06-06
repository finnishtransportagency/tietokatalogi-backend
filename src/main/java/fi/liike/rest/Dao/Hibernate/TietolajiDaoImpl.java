package fi.liike.rest.Dao.Hibernate;

import java.sql.SQLException;
import java.util.List;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.Tietolaji;
import fi.liike.rest.Model.TietolajiHistory;
import fi.liike.rest.Model.TietolajiKasite;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

public class TietolajiDaoImpl extends SearchDaoImpl implements MainDao {

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(Tietolaji.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(Tietolaji.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent, "tietolaji_seq", new TietolajiHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(session, content, new TietolajiHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		return super.getResources(TietolajiKasite.class);
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(Tietolaji.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(Tietolaji.class, TietolajiHistory.class, id, new TietolajiHistory(),
				deleteContent, remoteUser);
	}
}
