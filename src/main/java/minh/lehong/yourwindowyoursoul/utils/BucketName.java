package minh.lehong.yourwindowyoursoul.utils;

public enum BucketName {

    BUCKET_NAME("minhlehong-springbootawss3");
    private final String bucketName;
    public String getBucketName() {
        return bucketName;
    }
    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
