package fi.liike.rest.Dao;

import java.sql.SQLException;
import java.util.List;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.DaoContent;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import org.hibernate.Session;

public interface MainDao {
	ModelResults getFiltered(SearchContent searchContent);

	Haettava get(int id);

	List<Haettava> getAll();

	Haettava save(Session session, DaoContent saveContent) throws SQLException;

	Haettava update(Session session, DaoContent content);

	void delete(int id, DaoContent deleteContent, String remoteUser) throws SQLException;

	List<KasiteArvoContent> getResources();
}
