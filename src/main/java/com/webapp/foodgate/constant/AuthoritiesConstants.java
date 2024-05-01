package com.webapp.foodgate.constant;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AuthoritiesConstants {
    /**
     * ANONYMOUS
     */
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }

    public static List<String> getAllPermissions() {
        List<String> constants = new ArrayList<>();
        Field[] fields = AuthoritiesConstants.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                try {
                    constants.add((String) field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return constants;
    }

    /**
     * @return list consumer permissions
     */
    public static List<String> getConsumerPermissions(){
        List<String> constants = Arrays.asList(GET_PROFILE_CONSUMER,
                UPDATE_PROFILE_CONSUMER,
                DELETE_CONSUMER);
        return constants;
    }

    /**
     * @return list manager permissions
     */
    public static List<String> getManagerPermissions(){
        List<String> constants = Arrays.asList(UPDATE_MANAGER,
                GET_MANAGER,
                DELETE_MANAGER);
        return constants;
    }

    /**
     * start Permission
     */

    /**
     * start permission - group
     */
    public static final String CREATE_GROUP = "GRO_C";
    public static final String ADD_PERMISSION_GROUP = "GRO_ADD_PER_GRO";
    public static final String GET_LIST_GROUP = "GRO_L";
    public static final String GET_GROUP = "GRO_V";
    public static final String UPDATE_GROUP = "GRO_U";
    public static final String DELETE_GROUP = "GRO_D";
    /**
     * start permission - permission
     */
    public static final String CREATE_PERMISSION = "PER_C";
    public static final String GET_LIST_PERMISSION = "PER_L";
    public static final String GET_PERMISSION = "PER_V";
    public static final String UPDATE_PERMISSION = "PER_U";
    public static final String DELETE_PERMISSION = "PER_D";
    /**
     * start permission - member
     */
    public static final String CREATE_ADMIN_MEMBER = "MEM_ADMIN_C";
    public static final String GET_LIST_MEMBER = "MEM_L";
    public static final String GET_MEMBER = "MEM_V";
    public static final String UPDATE_MEMBER = "MEM_U";
    public static final String DELETE_MEMBER = "MEM_D";

    /**
     * start permission - consumer
     */
    // PERMITALL() - SIGN_UP_CONSUMER
    public static final String SIGN_UP_CONSUMER = "CON_SIGNUP";
    public static final String GET_PROFILE_CONSUMER = "CON_PROFILE";
    public static final String UPDATE_PROFILE_CONSUMER = "CON_U_PROFILE";
    public static final String GET_LIST_CONSUMER = "CON_L";
    public static final String DELETE_CONSUMER = "CON_D";
    /**
     * start permission - manager
     */
    public static final String CREATE_MANAGER = "MAN_C";
    public static final String GET_LIST_MANAGER = "MAN_L";
    public static final String GET_MANAGER = "MAN_V";
    public static final String UPDATE_MANAGER = "MAN_U";
    public static final String DELETE_MANAGER = "MAN_D";
    /**
     * start permission - Address
     */
    public static final String CREATE_ADDRESS = "ADD_C";
    public static final String GET_LIST_ADDRESS = "ADD_L";
    public static final String GET_ADDRESS = "ADD_V";
    public static final String UPDATE_ADDRESS = "ADD_U";
    public static final String DELETE_ADDRESS = "ADD_D";
    /**
     * start permission - food
     */
    public static final String CREATE_FOOD = "FO_C";
    public static final String GET_LIST_FOOD = "FO_L";
    public static final String GET_FOOD = "FO_V";
    public static final String UPDATE_FOOD = "FO_U";
    public static final String DELETE_FOOD = "FO_D";
    /**
     * start permission - orderItem
     */
    public static final String CREATE_ORDER_ITEM = "OR_IT_C";
    public static final String GET_LIST_ORDER_ITEM = "OR_IT_L";
    public static final String GET_ORDER_ITEM = "OR_IT_V";
    public static final String UPDATE_ORDER_ITEM = "OR_IT_U";
    public static final String DELETE_ORDER_ITEM = "OR_IT_D";
    /**
     * start permission - order
     */
    public static final String CREATE_ORDER = "OR_C";
    public static final String GET_LIST_ORDER = "OR_L";
    public static final String GET_ORDER = "OR_V";
    public static final String UPDATE_ORDER = "OR_U";
    public static final String DELETE_ORDER = "OR_D";
    /**
     * start permission - cart
     */
    public static final String CREATE_CART = "CAR_C";
    public static final String GET_LIST_CART = "CAR_L";
    public static final String GET_CART = "CAR_V";
    public static final String UPDATE_CART = "CAR_U";
    public static final String DELETE_CART = "CAR_D";

    /**
     * start permission - rating
     */
    public static final String CREATE_RATING = "RAT_C";
    public static final String GET_LIST_RATING = "RAT_L";
    public static final String GET_RATING = "RAT_V";
    public static final String UPDATE_RATING = "RAT_U";
    public static final String DELETE_RATING = "RAT_D";

    /**
     * start permission - category
     */
    public static final String CREATE_CATEGORY = "CAT_C";
    public static final String GET_LIST_CATEGORY = "CAT_L";
    public static final String GET_CATEGORY = "CAT_V";
    public static final String UPDATE_CATEGORY = "CAT_U";
    public static final String DELETE_CATEGORY = "CAT_D";
    /**
     * start permission - payment
     */
    public static final String CREATE_PAYMENT = "PAY_C";
    public static final String GET_LIST_PAYMENT = "PAY_L";
    public static final String GET_PAYMENT = "PAY_V";
    public static final String UPDATE_PAYMENT = "PAY_U";
    public static final String DELETE_PAYMENT = "PAY_D";
    /**
     * start permission - wishlist
     */
    public static final String CREATE_WISH_LIST = "WIS_C";
    public static final String GET_LIST_WISH_LIST = "WIS_L";
    public static final String GET_WISH_LIST = "WIS_V";
    public static final String UPDATE_WISH_LIST = "WIS_U";
    public static final String DELETE_WISH_LIST = "WIS_D";
    /**
     * start permission - restaurant
     */
    public static final String CREATE_RESTAURANT = "RES_C";
    public static final String GET_LIST_RESTAURANT = "RES_L";
    public static final String GET_RESTAURANT = "RES_V";
    public static final String UPDATE_RESTAURANT = "RES_U";
    public static final String DELETE_RESTAURANT = "RES_D";
    /**
     * start permission - promote
     */
    public static final String CREATE_PROMOTE = "PRO_C";
    public static final String GET_LIST_PROMOTE = "PRO_L";
    public static final String GET_PROMOTE = "PRO_V";
    public static final String UPDATE_PROMOTE = "PRO_U";
    public static final String DELETE_PROMOTE = "PRO_D";

}
