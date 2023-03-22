package minh.lehong.yourwindowyoursoul.constant;

public class Constant {
    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";

    public static final String CREATE_DATE = "createDate";

    public static final String SORT_DEFAULT = "createDate:desc";

    public static final String PAGE = "1";
    public static final String LIMIT = "10";

    public static final String COLON = ":";

    public static final String CATEGORY = "category";
    public static final String MAIN_IMAGE = "main_image";
    public static final String FILE = "file";

    public static final int SIGNUP_ERROR = 0;
    public static final int LOGIN_ERROR = 0;
    public static final int HAD_ACCOUNT = 2;
    public static final int PASSWORD_NULL = 3;
    public static final int SIGNUP_SUCCESS = 1;
    public static final int LOGIN_SUCCESS = 1;

    public static final int RETURN_OK = 1;
    public static final int IS_DISABLE = 2;
    public static final int SESSION_EXPIRED_CODE = 980;
    public static final String RETURN_CODE = "return_code";
    public static final String MESSAGE_TEXT = "message_text";
    public static final String PERCENT_IMPORTED = "percent_imported";
    public static final String CONTEXT_ID = "contextID";
    public static final String BRANCH_ID = "branch";
    public static final String AUTHORIZATION = "Authorization";
    public static final String JOB_IMPORT_BRANCH = "importBranchJob";
    public static final String JOB_IMPORT_CUSTOMER = "importCustomerJob";
    public static final String JOB_IMPORT_SHIPTO = "importShiptoJob";
    public static final String JOB_UPDATE_SECURITY_POLICY = "updateSecurityPolicyJob";
    public static final int IMPORT_COMPLETED = 100;
    public static final String JOB_PARAMETER_TIME = "time";
    public static final int IS_DELETED = 1;
    public static final int NOT_DELETED = 0;
    public static final int IS_ACTIVE = 1;
    public static final String ID = "id";
    public static final String E_COMMERCE_VERSION = "eCommerceVersion";
    public static final int PASSWORD_CHANGE_REQUIRED_CODE = 976;
    public static final String ATTRIBUTES = "attributes";
    public static final String CUSTOMER_TYPE = "customer";
    public static final String PAGE_LIMIT = "page_limit";
    public static final String PAGE_OFFSET = "page_offset";
    public static final String ENDPOINT_NAME = "endpoint";
    public static final String PATH = "path";
    public static final String PAYMENT_SETTING ="payment_setting";
    public static final String CALL_API_DMSI ="direct:call-api-dmsi";
    public static final String CALL_API_WORLDPAY ="direct:call-api-worldpay";
    public static final String TRANSACTION_SETUP_ID = "TransactionSetupID";
    public static final String VALUE_SEARCH = "";
    public static final String CUSTOMER_GUID = "";
    public static final String CUS_SHIP_TO_GUID = "";
    public static final String FILTER_GROUP_GUID = "groupGUID";
    public static final String RESPONSE_DATA = "response_data";

    public static final String TYPE = "type";
    public static final String DATA = "data";
    public static final String META = "meta";
    public static final String LINKS = "links";
    public static final String META_PAGE = "page";
    public static final String RESPONSE = "response";

    public static final String HEADER_CONTEXT_ID = "contextid";
    public static final String HEADER_BRANCH_ID = "branchid";
    public static final String HEADER_CUST_SHIP_TO_GUID = "custshiptoguid";
    public static final String HEADER_SALE_TYPE = "saletype";
    public static final String HEADER_IS_CART = "iscart";
    public static final String DATE_FORMAT_DDMMYYYY_SLASH = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DDMMYYYYHHMMSS_HYPHEN = "dd-MM-yyyy HH:mm:ss";
    public static final String SEPARATE_VARIANT = "__";

    public static final String SEPARATE_VARIANT_COLON = "::";
    public static final String PERCENT_100 = "100";
    public static final String ZERO = "0";

    private Constant() {
    }

    public interface TRANSACTION_STATUS {
        String PENDING = "Pending";
        String COMPLETE = "Complete";
    }
}
