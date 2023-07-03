package minh.lehong.yourwindowyoursoul.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;
import java.util.Map;

public interface FileStoreS3Service {
    String save(String fileName, InputStream inputStream, ObjectMetadata metaData);
}
