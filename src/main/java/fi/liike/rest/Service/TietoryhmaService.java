package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.TietoryhmaDaoImpl;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.api.*;
import fi.liike.rest.api.Converter.TietoryhmaConverter;
import fi.liike.rest.api.dto.TietoryhmaDto;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TietoryhmaService extends MainService implements Service {

	private MainDao dao;
	private TietoryhmaConverter converter;
	private JoinService joinTietoryhmaPaatietoryhmaService;
	private JoinService joinTietoryhmaTietovarantoService;
	private final Logger LOG = LoggerFactory.getLogger(TietoryhmaService.class);

	public TietoryhmaService() {
		dao = new TietoryhmaDaoImpl();
		converter = new TietoryhmaConverter();
		joinTietoryhmaPaatietoryhmaService = new JoinTietoryhmaPaatietoryhmaService();
		joinTietoryhmaTietovarantoService = new JoinTietoryhmaTietovarantoService();
	}

	@Override
	public ContentDto save(ContentDto content) throws SQLException {
		return save(null, content);
	}

	@Override
	public ContentDto save(Session session, ContentDto content) throws SQLException {
		DaoContent saveContent = new DaoContent();
		saveContent.setHaettava(converter.dtoToDomain(content));

		Integer paaryhmaParentId = ((TietoryhmaDto) content).getPaatietoryhma();
		if (paaryhmaParentId != null) {
			saveContent.addJoinDao(
					joinTietoryhmaPaatietoryhmaService.getDao(paaryhmaParentId, content.getRivimuokkaajatunnus()));
		}

		Integer tietovarantoParentId = ((TietoryhmaDto) content).getTietovaranto();
		if (tietovarantoParentId != null) {
			saveContent.addJoinDao(
					joinTietoryhmaTietovarantoService.getDao(tietovarantoParentId, content.getRivimuokkaajatunnus()));
		}

		return converter.modelToDto(dao.save(session, saveContent), paaryhmaParentId, tietovarantoParentId);
	}

	@Override
	public ContentDto update(ContentDto content) {
		return update(null, content);
	}

	@Override
	public ContentDto update(Session session, ContentDto content) {
		DaoContent saveContent = new DaoContent();
		saveContent.setHaettava(converter.dtoToDomain(content));

		Integer paaryhmaParentId = ((TietoryhmaDto) content).getPaatietoryhma();
		if (paaryhmaParentId != null) {
			saveContent.addJoinDao(
					joinTietoryhmaPaatietoryhmaService.getDao(paaryhmaParentId, content.getRivimuokkaajatunnus()));
		}

		Integer tietovarantoParentId = ((TietoryhmaDto) content).getTietovaranto();
		if (tietovarantoParentId != null) {
			saveContent.addJoinDao(
					joinTietoryhmaTietovarantoService.getDao(tietovarantoParentId, content.getRivimuokkaajatunnus()));
		}

		return converter.modelToDto(dao.update(session, saveContent), paaryhmaParentId, tietovarantoParentId);
	}

	@Override
	public Integer delete(int id, String remoteUser) {
		try {
			DaoContent deleteContent = new DaoContent();

			Integer paaryhmaParentNodeId = joinTietoryhmaPaatietoryhmaService.getParentNodeId(id);
			if (paaryhmaParentNodeId != null) {
				deleteContent.addJoinDao(
						joinTietoryhmaPaatietoryhmaService
								.getDao(joinTietoryhmaPaatietoryhmaService.getParentNodeId(id), remoteUser));
			}

			Integer tietovarantoParentNodeId = joinTietoryhmaTietovarantoService.getParentNodeId(id);
			if (tietovarantoParentNodeId != null) {
				deleteContent.addJoinDao(
						joinTietoryhmaTietovarantoService
								.getDao(joinTietoryhmaTietovarantoService.getParentNodeId(id), remoteUser));
			}

			dao.delete(id, deleteContent, remoteUser);
			return id;
		} catch (SQLException e) {
			LOG.error("Unable to do a delete transaction. Error message: " + e.getMessage());
			return null;
		}
	}

	@Override
	public DtoResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(converter, dao, searchContent, joinTietoryhmaPaatietoryhmaService);
	}

	@Override
	public ContentDto get(int id) {
		return super.get(converter, dao, id, joinTietoryhmaPaatietoryhmaService.getParentNodeId(id));
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		List<KasiteArvoContent> resources = new ArrayList<KasiteArvoContent>();
		ResourceTool resourceTool = new ResourceTool();
		resources.addAll(resourceTool.createResources(Catalogue.PAATIETORYHMA));
		resources.addAll(resourceTool.createResources(Catalogue.TIETOVARANTO));
		return resources;
	}

	@Override
	public List<ContentDto> getAll() {
		return super.getAll(converter, dao, joinTietoryhmaPaatietoryhmaService);
	}

}
