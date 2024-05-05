package com.webapp.foodgate.constant;

public class UserBaseConstant {
    /**
     * status of object
     */

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_PENDING = 0;
    public static final int STATUS_LOCKED = -1;
    public static final int STATUS_DELETE = -2;

    public static final int[] STATUS = {STATUS_ACTIVE, STATUS_PENDING, STATUS_LOCKED, STATUS_DELETE};

    /**
     * ADMIN - all roles
     * manager - manager a restaurant
     * consumer - buy food
     */
    public static final Integer USER_KIND_ADMIN = 1;
    public static final Integer USER_KIND_MANAGER = 2;
    public static final Integer USER_KIND_CONSUMER = 3;

    public static final int[] KIND = {USER_KIND_ADMIN, USER_KIND_MANAGER, USER_KIND_CONSUMER};

    /**
     * isFemale
     */
    public static final Integer IS_FEMALE = 1;
    public static final Integer IS_MALE = 2;
}
