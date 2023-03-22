//package minh.lehong.yourwindowyoursoul.utils;
//
//import com.nimbusds.jose.shaded.json.parser.ParseException;
//import minh.lehong.yourwindowyoursoul.payload.request.accesstoken.EPTokenGenerationRequestModel;
//import minh.lehong.yourwindowyoursoul.payload.request.accesstoken.EPTokenGenerationUserModel;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.commons.lang3.StringUtils;
//import com.nimbusds.jose.shaded.json.JSONObject;
//import com.nimbusds.jose.shaded.json.parser.JSONParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EPAccessTokenUtil {
//    private static final String TYPE = "token";
//    private static final String AUTHENTICATION_MECHANISM = "password";
//    private static final String SPLIT_BY = "\\.";
//    private static final String EXPIRED_TIME = "exp";
////    private static final Logger logger = LoggerFactory.getLogger(EPAccessTokenUtil.class);
//
//    public static long getExpiredTime(final String accessTokenUser)
//    {
//        if (StringUtils.isNotEmpty(accessTokenUser))
//        {
//            try
//            {
//                final String[] splitStrings = accessTokenUser.split(SPLIT_BY);
//                final String base64EncodedBody = splitStrings[1];
//                final Base64 base64Url = new Base64(true);
//                final String body = new String(base64Url.decode(base64EncodedBody));
//                final JSONParser jsonParser = new JSONParser();
//                final JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
//                final int expiredDate = (int) jsonObject.get(EXPIRED_TIME);
//                //				The Date constructor accepts the time as long in milliseconds, not seconds.so need to multiply it by 1000
//                //				final Date expired = new Date(expiredDate*1000);
//                return expiredDate;
//            }
//            catch (final ParseException e)
//            {
//                logger.error("checkExpireAccessTokenUser {} " + e.getMessage());
//            }
//        }
//        return 0;
//    }
//    public static String getEPUserIdToken(String accessTokenUser)
//    {
//        if (StringUtils.isEmpty(accessTokenUser) || accessTokenUser.contains("Basic"))
//        {
//            return null;
//        }
//        if (StringUtils.isNotEmpty(accessTokenUser) && !accessTokenUser.equals("null"))
//        {
//            final String[] split_string = accessTokenUser.split(SPLIT_BY);
//            final String base64EncodedBody = split_string[1];
//            final Base64 base64Url = new Base64(true);
//            final String body = new String(base64Url.decode(base64EncodedBody));
//            final JSONParser jsonParser = new JSONParser();
//            try
//            {
//                final JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
//                return (String) jsonObject.get("sub");
//
//            }
//            catch (final ParseException e)
//            {
//                logger.error("getEPUserIdToken {} " + e.getMessage());
//            }
//        }
//        return null;
//    }
//
//    public static EPTokenGenerationRequestModel buildDataModelEP(final EPTokenGenerationUserModel user)
//    {
//        final EPTokenGenerationRequestModel requestModel = new EPTokenGenerationRequestModel();
//        final EPTokenGenerationUserModel dataModel = new EPTokenGenerationUserModel();
//        dataModel.setEmail(user.getEmail());
//        dataModel.setPassword(user.getPassword());
//        dataModel.setAuthenticationMechanism(AUTHENTICATION_MECHANISM);
//        dataModel.setType(TYPE);
//        requestModel.setData(dataModel);
//        return requestModel;
//    }
//}
