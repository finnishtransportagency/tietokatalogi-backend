package fi.liike.rest.Dao.aws;

import fi.liike.config.Configurations;
import fi.liike.rest.util.S3ClientUtil;
import org.apache.pdfbox.io.IOUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

public class FrontpageS3Dao {
    private final String bucketName;

    public FrontpageS3Dao() {
        Configurations.readConfigurations();
        this.bucketName = Configurations.bucketName;
    }

    public ByteBuffer getImage(String name) throws IOException {
        S3Client s3 = S3ClientUtil.getInstance();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(this.bucketName)
                .key(name)
                .build();
        ResponseInputStream<GetObjectResponse> response = s3.getObject(getObjectRequest);
        return ByteBuffer.wrap(IOUtils.toByteArray(response));
    }

    public void putImage(ByteBuffer imageBytes, String imageName) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(imageName)
                .build();
        S3ClientUtil.getInstance().putObject(
                objectRequest, RequestBody.fromByteBuffer(imageBytes)
        );
    }
}
