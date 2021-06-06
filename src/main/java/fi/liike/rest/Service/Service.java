package fi.liike.rest.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.KasiteArvoContent;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.auth.InvalidTietokatalogiDataException;
import org.hibernate.Session;

public interface Service {

	DtoResults getFiltered(SearchContent searchContent);

	List<ContentDto> getAll();

	ContentDto save(ContentDto content) throws SQLException, IOException;

	ContentDto save(Session session, ContentDto content) throws SQLException, IOException;

	ContentDto update(ContentDto content) throws IOException, SQLException, InvalidTietokatalogiDataException;

	ContentDto update(Session session, ContentDto content) throws IOException, SQLException;

	Integer delete(int id, String remoteUser);

	ContentDto get(int id);

	List<KasiteArvoContent> getResources();


}
