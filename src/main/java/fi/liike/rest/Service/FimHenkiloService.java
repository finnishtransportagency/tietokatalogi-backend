package fi.liike.rest.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fi.liike.config.Configurations;
import fi.liike.rest.Dao.Hibernate.HenkiloDao;
import fi.liike.rest.Model.Henkilo;
import fi.liike.rest.Model.HenkiloTemp;
import fi.liike.rest.api.Converter.HenkiloConverter;
import fi.liike.rest.api.dto.HenkiloDto;
import fi.liike.rest.util.http.HttpClient;
import fi.liike.rest.util.http.LiikeHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Table;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FimHenkiloService {

    private final HttpClient httpClient;
    private final ObjectMapper xmlMapper;
    private final HenkiloConverter henkiloConverter;
    private final HenkiloDao henkiloDao;
    private final Logger LOG = LoggerFactory.getLogger(HenkiloService.class);

    private String fimFetchAllPersons = "Person.svc/getsimplesql";
    private String baseFIMurl;

    public FimHenkiloService(String baseFIMurl, HttpClient httpClient) {
        this.httpClient = httpClient;
        this.xmlMapper = new XmlMapper();
        this.baseFIMurl = baseFIMurl;
        this.henkiloConverter = new HenkiloConverter();
        this.henkiloDao = new HenkiloDao();
    }

    public FimHenkiloService() {
        this.httpClient = new HttpClient();
        this.xmlMapper = new XmlMapper();
        this.henkiloConverter = new HenkiloConverter();
        this.henkiloDao = new HenkiloDao();
        Configurations.readConfigurations();
        this.baseFIMurl = Configurations.baseFIMurl;
    }


    public List<HenkiloTemp> fetchAndSaveAllPersonsFromFIM() throws IOException {
        List<HenkiloTemp> henkiloList = fetchAllPersonsFromFIM();
        if (henkiloList != null && henkiloList.size() > 0) {
            LOG.info("Merging Henkilolist");
            mergeHenkiloList(henkiloList);
        }
        return henkiloList;
    }


    private List<HenkiloTemp> fetchAllPersonsFromFIM() throws IOException {
        String url = baseFIMurl + fimFetchAllPersons;
        LOG.debug("Building FIM request to " + url);
        LiikeHttpResponse response = httpClient.get(url);
        validateResponse(response);
        List<HenkiloDto> contentDtoList = httpResponseToPersonList(response);
        List<HenkiloTemp> henkiloList = null;

        if (contentDtoList != null) {
            henkiloList = new ArrayList<HenkiloTemp>();
            for (HenkiloDto contentDto : contentDtoList) {
                henkiloList.add(henkiloConverter.henkiloDtoToHenkiloTemp(contentDto));
            }
        }

        return henkiloList;
    }

    public void mergeHenkiloList(List<HenkiloTemp> henkiloList) {
        List<Object> objList = new ArrayList<>();
        objList.addAll(henkiloList);

        LOG.info("Merging new FIM person list");
        String tempTable = HenkiloTemp.class.getAnnotation(Table.class).name();
        String targetTable = Henkilo.class.getAnnotation(Table.class).name();
        String targetTableIdSequence = Henkilo.seqName;
        String idColumn = Henkilo.idColumn;
        String matchingColumn = Henkilo.mergeMatchingColumn;
        String updateColumn = Henkilo.mergeUnmatchUpdateColumn;
        Integer updateValue = Henkilo.mergeUnmatchUpdateValue;

        List<String> updateColumns = Henkilo.getUpdateColumnsForMerge();
        List<String> insertColumns = Henkilo.getInsertColumnsForMerge();

        henkiloDao.saveTempTableAndMergeTables(objList, tempTable, targetTable, targetTableIdSequence,
                idColumn, Collections.singletonList(matchingColumn), updateColumn, updateValue, updateColumns, insertColumns);
    }

    private void validateResponse(LiikeHttpResponse resp) throws IOException {
        if (resp != null && resp.getStatusCode().equals(200)
                && resp.getResponse() != null && !resp.getResponse().isEmpty()) {
            LOG.debug("FimHenkiloService successfully fetched new FIM content");
        } else {
            if (resp == null) {
                LOG.error("Response object from FIM fetch was null");
            } else if (!resp.getStatusCode().equals(200)) {
                LOG.error("FIM fetch responded with status code " + resp.getStatusCode());
            } else if (resp.getResponse() == null) {
                LOG.error("Response from FIM fetch was null");
            } else {
                LOG.error("Response from FIM fetch was empty");
            }
            throw new IOException();
        }
    }

    private List<HenkiloDto> httpResponseToPersonList(LiikeHttpResponse response) {
        String content = response.getResponse();
        try {
            return xmlMapper.readValue(content, new TypeReference<List<HenkiloDto>>() {});
        } catch (IOException e) {
            LOG.error("Can not convert FIM XML content to HenkiloDto list: " + e.getMessage());
            return null;
        }
    }
}
