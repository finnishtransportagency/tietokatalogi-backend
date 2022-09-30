package fi.liike.rest.Service;

import fi.liike.rest.Dao.MainDao;
import fi.liike.rest.Model.Haettava;
import fi.liike.rest.api.ContentDto;
import fi.liike.rest.api.DtoResults;
import fi.liike.rest.api.ModelResults;
import fi.liike.rest.api.SearchContent;
import fi.liike.rest.api.Converter.Converter;
import fi.liike.rest.api.Converter.MinimalConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class MinimalFetchService extends MainService {
    private final MainDao dao;
    private final MinimalConverter converter;

    public MainDao getDao() {
        return dao;
    }

    public Converter getConverter() {
        return converter;
    }

    public MinimalFetchService(MainDao dao, MinimalConverter converter) {
        this.dao = dao;
        this.converter = converter;
    }

    public DtoResults getFilteredMinimal(SearchContent searchContent) {
        ModelResults filtered = dao.getFiltered(searchContent);
        ArrayList<ContentDto> dtoResults = new ArrayList<>();
        List<Haettava> modelResults = filtered.getHaettavat();
        for (Haettava haettava : modelResults) {
            dtoResults.add(converter.modelToMinimalDto(haettava));
        }
        return new DtoResults(dtoResults, filtered.getTotalCount());
    }
}
