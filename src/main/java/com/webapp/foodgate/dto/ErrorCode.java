package com.webapp.foodgate.dto;

public class ErrorCode {
    /**
     * General error code
     */
    public static final String GENERAL_ERROR_REQUIRE_PARAMS = "ERROR-GENERAL-0000";
    public static final String GENERAL_ERROR_STORE_LOCKED = "ERROR-GENERAL-0001";
    public static final String GENERAL_ERROR_ACCOUNT_LOCKED = "ERROR-GENERAL-0002";
    public static final String GENERAL_ERROR_SHOP_LOCKED = "ERROR-GENERAL-0003";
    public static final String GENERAL_ERROR_STORE_NOT_FOUND = "ERROR-GENERAL-0004";
    public static final String GENERAL_ERROR_ACCOUNT_NOT_FOUND = "ERROR-GENERAL-0005";

    /**
     * Starting error code MEMBER
     */
    public static final String MEMBER_ERROR_UNKNOWN = "ERROR-MEMBER-0000";
    public static final String MEMBER_ERROR_USERNAME_EXIST = "ERROR-MEMBER-0001";
    public static final String MEMBER_ERROR_NOT_FOUND = "ERROR-MEMBER-0002";
    public static final String MEMBER_ERROR_WRONG_PASSWORD = "ERROR-MEMBER-0003";
    public static final String MEMBER_ERROR_WRONG_HASH_RESET_PASS = "ERROR-MEMBER-0004";
    public static final String MEMBER_ERROR_LOCKED = "ERROR-MEMBER-0005";
    public static final String MEMBER_ERROR_OPT_INVALID = "ERROR-MEMBER-0006";
    public static final String MEMBER_ERROR_LOGIN = "ERROR-MEMBER-0007";
    public static final String MEMBER_ERROR_MERCHANT_LOGIN_ERROR_DEVICE = "ERROR-MEMBER-0008";
    public static final String MEMBER_ERROR_MERCHANT_LOGIN_ERROR_STORE = "ERROR-MEMBER-0009";
    public static final String MEMBER_ERROR_MERCHANT_LOGIN_WRONG_STORE = "ERROR-MEMBER-0010";
    public static final String MEMBER_ERROR_MERCHANT_SERVICE_NOT_REGISTER = "ERROR-MEMBER-0011";
    public static final String MEMBER_ERROR_NOT_ALLOW_DELETE_SUPPER_ADMIN = "ERROR-MEMBER-0012";
    public static final String MEMBER_ERROR_EMAIL_EXIST = "ERROR-MEMBER-0013";
    public static final String MEMBER_ERROR_LOGIN_EXIST = "ERROR-MEMBER-0014";
    public static final String MEMBER_ERROR_PHONE_NUMBER_EXIST = "ERROR-MEMBER-0015";

    /**
     * Starting error code MANAGER
     **/

    public static final String MANAGER_ERROR_OWNER_RESTAURANT = "ERROR-MANAGER-0001";
    /**
     * Starting error code RESTAURANT
     */
    public static final String RESTAURANT_ERROR_NOT_FOUND = "ERROR-RESTAURANT-0001";
    /**
     * Starting error code GROUP
     */
    public static final String GROUP_ERROR_EXIST = "ERROR-GROUP-0001";
    public static final String GROUP_ERROR_NOT_FOUND = "ERROR-GROUP-0002";

    public static final String GROUP_ERROR_INVALID_STATUS = "ERROR-GROUP-0002";
    public static final String GROUP_ERROR_INVALID_KIND = "ERROR-GROUP-0003";
    /**
     * Starting error code PERMISSION
     */
    public static final String PERMISSION_ERROR_EXIST = "ERROR-PERMISSION-0001";

    public static final String PERMISSION_ERROR_NOT_FOUND = "ERROR-PERMISSION-0002";
    /**
     * Starting error code ADDRESS
     */
    public static final String ADDRESS_ERROR_NOT_FOUND = "ERROR-ADDRESS-0001";

    /**
     * Starting error code FOOD
     */
    public static final String FOOD_ERROR_NOT_FOUND = "ERROR-FOOD-0001";
    public static final String FOOD_ERROR_TITLE = "ERROR-FOOD-0002";


}
