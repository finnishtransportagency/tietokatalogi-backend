package fi.liike.rest.Service;

import java.util.ArrayList;
import java.util.List;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.Converter.Converter;

public class MainService {

	public DtoResults getFiltered(Converter converter, MainDao dao, SearchContent searchContent,
			JoinService... joinService) {
		ModelResults filtered = dao.getFiltered(searchContent);
		ArrayList<ContentDto> dtoResults = new ArrayList<ContentDto>();
		List<Haettava> modelResults = filtered.getHaettavat();
		for (Haettava haettava : modelResults) {
			Integer parentId1 = null;
			Integer parentId2 = null;
			if (joinService.length > 0)
				parentId1 = joinService[0].getParentNodeId(haettava.getTunnus());
			if (joinService.length > 1)
				parentId2 = joinService[1].getParentNodeId(haettava.getTunnus());
			dtoResults.add(converter.modelToDto(haettava, parentId1, parentId2));
		}
		return new DtoResults(dtoResults, filtered.getTotalCount());
	}

	public List<ContentDto> getAll(Converter converter, MainDao dao, JoinService... joinService) {
		ArrayList<ContentDto> dtoResults = new ArrayList<ContentDto>();
		for (Haettava haettava : dao.getAll()) {
			Integer parentId1 = null;
			Integer parentId2 = null;
			if (joinService.length > 0)
				parentId1 = joinService[0].getParentNodeId(haettava.getTunnus());
			if (joinService.length > 1)
				parentId1 = joinService[1].getParentNodeId(haettava.getTunnus());
			dtoResults.add(converter.modelToDto(haettava, parentId1, parentId2));
		}
		return dtoResults;
	}

	public ContentDto get(Converter converter, MainDao dao, int id, Integer... parentNodeId) {
		Haettava haettava = dao.get(id);
		if (haettava == null) return null;
		return converter.modelToDto(haettava, parentNodeId);
	}

}
