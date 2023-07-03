package minh.lehong.yourwindowyoursoul.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import minh.lehong.yourwindowyoursoul.service.FileStoreS3Service;
import minh.lehong.yourwindowyoursoul.utils.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Map;

@Service
public class FileStoreS3ServiceImpl implements FileStoreS3Service
{
    @Autowired
    private final AmazonS3 s3;

    public FileStoreS3ServiceImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String save(String fileName, InputStream inputStream, ObjectMetadata metaData) {
        try {
            PutObjectRequest request = new PutObjectRequest(BucketName.BUCKET_NAME.getBucketName(), fileName, inputStream, metaData)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(request);
            return s3.getUrl(BucketName.BUCKET_NAME.getBucketName(), fileName).toString();
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }
}
