package fi.liike.rest.Dao.Hibernate;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.FyysinenTietovaranto;
import fi.liike.rest.Model.FyysinenTietovarantoHistory;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class FyysinenDaoImpl extends SearchDaoImpl implements MainDao {

	@Override
	public ModelResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(FyysinenTietovaranto.class, searchContent);
	}

	@Override
	public Haettava get(int id) {
		return get(FyysinenTietovaranto.class, id);
	}

	@Override
	public Haettava save(Session session, DaoContent saveContent) throws SQLException {
		return super.save(session, saveContent,"fyysinentv_seq", new FyysinenTietovarantoHistory());
	}

	@Override
	public Haettava update(Session session, DaoContent content) {
		return super.update(session, content, new FyysinenTietovarantoHistory());
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		return null;
	}

	@Override
	public List<Haettava> getAll() {
		return super.getAll(FyysinenTietovaranto.class);
	}

	@Override
	public void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException {
		super.delete(FyysinenTietovaranto.class, FyysinenTietovarantoHistory.class, id,
				new FyysinenTietovarantoHistory(), deleteContent, remoteUser);
	}
}
