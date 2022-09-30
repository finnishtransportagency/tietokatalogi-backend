package fi.liike.rest.Service;

import fi.liike.rest.Dao.Hibernate.TietolajiDaoImpl;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.api.*;
import fi.liike.rest.api.Converter.TietolajiConverter;
import fi.liike.rest.api.dto.TietolajiDto;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TietolajiService extends MainService implements Service {

	private MainDao dao;
	private JoinService joinTietolajiLooginenService;
	private TietolajiConverter converter;
	private JoinService joinTietolajiTietoryhmaService;
	private final Logger LOG = LoggerFactory.getLogger(TietolajiService.class);

	public TietolajiService() {
		dao = new TietolajiDaoImpl();
		joinTietolajiLooginenService = new JoinTietolajiLooginenService();
		joinTietolajiTietoryhmaService = new JoinTietolajiTietoryhmaService();
		converter = new TietolajiConverter();
	}

	@Override
	public ContentDto save(ContentDto content) throws SQLException {
	    return save(null, content);
	}

	@Override
	public ContentDto save(Session session, ContentDto content) throws SQLException {
		DaoContent saveContent = new DaoContent();
		saveContent.setHaettava(converter.dtoToDomain(content));

		Integer looginenParentId = ((TietolajiDto) content).getLooginenTietovarantoTunnus();
		if (looginenParentId != null)
			saveContent.addJoinDao(
					joinTietolajiLooginenService.getDao(looginenParentId, content.getRivimuokkaajatunnus()));
		Integer tietoryhmaParentId = ((TietolajiDto) content).getTietoryhmatunnus();
		if (tietoryhmaParentId != null)
			saveContent.addJoinDao(
					joinTietolajiTietoryhmaService.getDao(tietoryhmaParentId, content.getRivimuokkaajatunnus()));
		return converter.modelToDto(dao.save(session, saveContent), looginenParentId, tietoryhmaParentId);
	}

	@Override
	public ContentDto update(ContentDto content) {
		return update(null, content);
	}

	@Override
	public ContentDto update(Session session, ContentDto content) {
		DaoContent saveContent = new DaoContent();
		saveContent.setHaettava(converter.dtoToDomain(content));

		Integer looginenParentId = ((TietolajiDto) content).getLooginenTietovarantoTunnus();
		if (looginenParentId != null)
			saveContent.addJoinDao(
					joinTietolajiLooginenService.getDao(looginenParentId, content.getRivimuokkaajatunnus()));
		Integer tietoryhmaParentId = ((TietolajiDto) content).getTietoryhmatunnus();
		if (tietoryhmaParentId != null)
			saveContent.addJoinDao(
					joinTietolajiTietoryhmaService.getDao(tietoryhmaParentId, content.getRivimuokkaajatunnus()));
		return converter.modelToDto(dao.update(session, saveContent), looginenParentId, tietoryhmaParentId);
	}

	@Override
	public Integer delete(int id, String remoteUser) {
		try {
			DaoContent deleteContent = new DaoContent();
			Integer looginenParentNodeId = joinTietolajiLooginenService.getParentNodeId(id);
			if (looginenParentNodeId != null)
				deleteContent.addJoinDao(
						joinTietolajiLooginenService.getDao(joinTietolajiLooginenService.getParentNodeId(id), remoteUser));
			Integer tietoryhmaParentId = joinTietolajiLooginenService.getParentNodeId(id);
			if (tietoryhmaParentId != null) {
				Integer tietoryhmaID = joinTietolajiTietoryhmaService.getParentNodeId(id);
				if (tietoryhmaID != null)
					deleteContent.addJoinDao(
						joinTietolajiTietoryhmaService.getDao(joinTietolajiTietoryhmaService.getParentNodeId(id), remoteUser));
			}
			dao.delete(id, deleteContent, remoteUser);
			return id;
		} catch (SQLException e) {
			LOG.error("Unable to do a handle database. Error message: " + e.getMessage());
			return null;
		}
	}

	@Override
	public DtoResults getFiltered(SearchContent searchContent) {
		return super.getFiltered(converter, dao, searchContent, joinTietolajiLooginenService,
				joinTietolajiTietoryhmaService);
	}

	@Override
	public ContentDto get(int id) {
		Integer looginenParentNodeId = joinTietolajiLooginenService.getParentNodeId(id);
		Integer tietoryhmaParentNodeId = joinTietolajiTietoryhmaService.getParentNodeId(id);
		return super.get(converter, dao, id, looginenParentNodeId, tietoryhmaParentNodeId);
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		List<KasiteArvoContent> resources = new ArrayList<KasiteArvoContent>();
		ResourceTool resourceTool = new ResourceTool();
		resources.addAll(dao.getResources());
		resources.addAll(resourceTool.createResources(Catalogue.LOOGINEN));
		resources.addAll(resourceTool.createResources(Catalogue.TIETORYHMA));
		return resources;
	}

	@Override
	public List<ContentDto> getAll() {
		return super.getAll(converter, dao, joinTietolajiLooginenService,
				joinTietolajiTietoryhmaService);
	}

}
