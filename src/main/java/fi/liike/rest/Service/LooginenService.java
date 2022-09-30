package fi.liike.rest.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fi.liike.rest.Model.Haettava;
import fi.liike.rest.Model.JoinJarjestelmaLooginen;
import fi.liike.rest.api.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.liike.rest.Dao.JoinPublicDao;
import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Dao.Hibernate.LooginenDaoImpl;
import fi.liike.rest.api.Converter.LooginenTietovarantoConverter;
import fi.liike.rest.api.dto.LooginenTietovarantoDto;

public class LooginenService extends MainService implements Service {

	private MainDao dao;
	private JoinService joinLooginenFyysinenService;
	private JoinJarjestelmaLooginenService joinJarjestelmaLooginenService;
	private LooginenTietovarantoConverter converter;
	private final Logger LOG = LoggerFactory.getLogger(LooginenService.class);

	public LooginenService() {
		dao = new LooginenDaoImpl();
		joinLooginenFyysinenService = new JoinLooginenFyysinenService();
		joinJarjestelmaLooginenService = new JoinJarjestelmaLooginenService();
		converter = new LooginenTietovarantoConverter();
	}

	class DaoContentWrapper {
		private final DaoContent saveContent;
		private final Integer fyysinenId;
		private final List<Integer> jarjestelmaIds;

		public DaoContentWrapper(DaoContent saveContent, Integer fyysinenId, List<Integer> jarjestelmaIds) {
			this.saveContent = saveContent;
			this.fyysinenId = fyysinenId;
			this.jarjestelmaIds = jarjestelmaIds;
		}

		public DaoContent getSaveContent() {
			return saveContent;
		}

		public Integer getFyysinenId() {
			return fyysinenId;
		}

		public List<Integer> getJarjestelmaIds() {
			return jarjestelmaIds;
		}
	}

	private DaoContentWrapper prepareDaoContent(ContentDto content) {
		DaoContent saveContent = new DaoContent();
		saveContent.setHaettava(converter.dtoToDomain(content));

		// Create joinDao for fyysinen-looginen join
		Integer parentId = ((LooginenTietovarantoDto) content).getFyysinenTietovarantoId();
		if (parentId != null) {
			JoinPublicDao joinLooginenFyysinenDao = joinLooginenFyysinenService.getDao(parentId,
					content.getRivimuokkaajatunnus());
			saveContent.addJoinDao(joinLooginenFyysinenDao);
		}

		// Create joinDao for jarjestelma-looginen joins
		List<JoinJarjestelmaLooginen> joinJarjestelmaLooginenList = new ArrayList<>();
		List<Integer> jarjestelmaIds = ((LooginenTietovarantoDto) content).getJarjestelmaIds();
		if (jarjestelmaIds != null) {
			for (Integer jarjestelmaId : jarjestelmaIds) {
				joinJarjestelmaLooginenList.add(new JoinJarjestelmaLooginen(jarjestelmaId, content.getTunnus()));
			}
			JoinPublicDao joinJarjestelmaLooginenDao = joinJarjestelmaLooginenService.getDao(
					joinJarjestelmaLooginenList, content.getRivimuokkaajatunnus());
			saveContent.addJoinDao(joinJarjestelmaLooginenDao);
		}
		return new DaoContentWrapper(saveContent, parentId, jarjestelmaIds);
	}

	@Override
	public ContentDto save(ContentDto content) throws SQLException, IOException {
		return save(null, content);
	}

	@Override
	public ContentDto save(Session session, ContentDto content) throws SQLException, IOException {
		DaoContentWrapper dataToSave = prepareDaoContent(content);
		Haettava savedLooginen = dao.save(session, dataToSave.getSaveContent());
		return converter.modelToDto(savedLooginen, dataToSave.getFyysinenId(), dataToSave.getJarjestelmaIds());
	}

	@Override
	public ContentDto update(ContentDto content) {
	    return update(null, content);
	}

	@Override
	public ContentDto update(Session session, ContentDto content) {
		DaoContentWrapper dataToSave = prepareDaoContent(content);
		Haettava savedLooginen = dao.update(session, dataToSave.getSaveContent());
		return converter.modelToDto(savedLooginen, dataToSave.getFyysinenId(), dataToSave.getJarjestelmaIds());
	}

	@Override
	public Integer delete(int id, String remoteUser) {
		try {
			DaoContent deleteContent = new DaoContent();
			Integer fyysinenId = joinLooginenFyysinenService.getParentNodeId(id);
			if (fyysinenId != null)
				deleteContent.addJoinDao(
						joinLooginenFyysinenService.getDao(joinLooginenFyysinenService.getParentNodeId(id), remoteUser));

			List<JoinJarjestelmaLooginen> joinJarjestelmaLooginenList = new ArrayList<>();
			List<Integer> jarjestelmaIds = joinJarjestelmaLooginenService.getJarjestelmaIdsOfLooginen(id);
			if (jarjestelmaIds != null) {
				for (Integer jarjestelmaId : jarjestelmaIds) {
					joinJarjestelmaLooginenList.add(new JoinJarjestelmaLooginen(jarjestelmaId, id));
				}
				deleteContent.addJoinDao(joinJarjestelmaLooginenService.getDao(joinJarjestelmaLooginenList, remoteUser));
			}
			dao.delete(id, deleteContent, remoteUser);
			return id;
		} catch (SQLException e) {
			LOG.error("Unable to delete content. Error Message:" + e.getMessage());
			return null;
		}
	}

	/**
	 * The main service getFiltered is overridden here so that we can return
	 * a _list_ of many jarjestelmaIds instead of a single parentId.
	 *
	 * If instead of ids we needed to return a list of jarjestelma _entities_, an
	 * implementation like TietojarjestelmapalveluFetch and super.getFiltered might be
	 * more suitable.
	 */
	@Override
	public DtoResults getFiltered(SearchContent searchContent) {
		ModelResults filtered = dao.getFiltered(searchContent);
		ArrayList<ContentDto> dtoResults = new ArrayList<ContentDto>();
		List<Haettava> modelResults = filtered.getHaettavat();
		for (Haettava haettava : modelResults) {
			Integer looginenId = haettava.getTunnus();
			dtoResults.add(converter.modelToDto(haettava, this.joinLooginenFyysinenService.getParentNodeId(looginenId),
					this.joinJarjestelmaLooginenService.getJarjestelmaIdsOfLooginen(looginenId)));
		}
		return new DtoResults(dtoResults, filtered.getTotalCount());
	}

	@Override
	public ContentDto get(int id) {
		Integer fyysinenId = joinLooginenFyysinenService.getParentNodeId(id);
		List<Integer> jarjestelmaIds = joinJarjestelmaLooginenService.getJarjestelmaIdsOfLooginen(id);
		Haettava haettava = dao.get(id);
		return converter.modelToDto(haettava, fyysinenId, jarjestelmaIds);
	}

	@Override
	public List<KasiteArvoContent> getResources() {
		ResourceTool resourceTool = new ResourceTool();
		List<KasiteArvoContent> resources = dao.getResources();
		resources.addAll(resourceTool.createResources(Catalogue.FYYSINEN));
		resources.addAll(resourceTool.createResources(Catalogue.JARJESTELMA));
		return resources;
	}

	@Override
	public List<ContentDto> getAll() {
		return super.getAll(converter, dao, joinLooginenFyysinenService);
	}

}
