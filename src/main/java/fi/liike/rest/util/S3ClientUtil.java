package fi.liike.rest.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class S3ClientUtil {
    private static final Region region = Region.EU_WEST_1;

    private S3ClientUtil() {
    }

    private static S3Client instance;

    public static S3Client getInstance() {
        if (instance == null) {
            instance = S3Client.builder()
                    .region(region)
                    .build();
        }
        return instance;
    }
}
