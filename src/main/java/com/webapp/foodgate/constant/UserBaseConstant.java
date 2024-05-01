package com.webapp.foodgate.constant;

public class UserBaseConstant {
    /**
     * status of object
     */

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_PENDING = 0;
    public static final int STATUS_LOCKED = -1;
    public static final int STATUS_DELETE = -2;

    /**
     * ADMIN - all roles
     * manager - manager a restaurant
     * consumer - buy food
     */
    public static final Integer USER_KIND_ADMIN = 1;
    public static final Integer USER_KIND_MANAGER = 2;
    public static final Integer USER_KIND_CONSUMER = 3;
    /**
     * isFemale
     */
    public static final Integer IS_FEMALE = 1;
    public static final Integer IS_MALE = 2;
}
