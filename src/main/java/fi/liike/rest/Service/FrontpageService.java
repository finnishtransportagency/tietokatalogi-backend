package fi.liike.rest.Service;

import com.sun.jersey.core.header.FormDataContentDisposition;
import fi.liike.rest.Dao.Hibernate.FrontpageDao;
import fi.liike.rest.Dao.aws.FrontpageS3Dao;
import fi.liike.rest.Model.Frontpage;
import fi.liike.rest.api.Converter.FrontpageConverter;
import fi.liike.rest.api.dto.FrontpageDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

public class FrontpageService extends MainService {
    private FrontpageDao dao;
    private FrontpageS3Dao s3Dao;
    private FrontpageConverter converter;

    public FrontpageService() {
        this.dao = new FrontpageDao();
        this.s3Dao = new FrontpageS3Dao();
        this.converter = new FrontpageConverter();
    }

    public void save(FrontpageDto dto) {
        dao.save(converter.dtoToDomain(dto));
    }

    public Optional<FrontpageDto> get() {
        Optional<Frontpage> frontpage = dao.getSingle();
        return frontpage.map(converter::modelToDto);
    }

    public ByteBuffer getImage(String name) throws IOException {
        return s3Dao.getImage(name);
    }

    public String uploadImage(InputStream inputStream, FormDataContentDisposition fileDetail) throws IOException {
        String extension = FilenameUtils.getExtension(fileDetail.getFileName());
        UUID uuid = UUID.randomUUID();
        String randomName = uuid + (extension.isEmpty() ? "" : "." + extension);
        ByteBuffer byteBuffer = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        s3Dao.putImage(byteBuffer, randomName);
        return randomName;
    }
}
