package com.webapp.foodgate;

import com.webapp.foodgate.component.AuditorAwareImpl;
import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.entities.*;
import com.webapp.foodgate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.member;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
public class FoodgateApplication implements CommandLineRunner {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private ManagerRepository managerRepository;

    public FoodgateApplication(GroupRepository groupRepository,
                               PermissionRepository permissionRepository,
                               MemberRepository memberRepository,
                               AddressRepository addressRepository,
                               ConsumerRepository consumerRepository,
                               ManagerRepository managerRepository) {
        this.groupRepository = groupRepository;
        this.permissionRepository = permissionRepository;
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.consumerRepository = consumerRepository;
        this.managerRepository = managerRepository;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(FoodgateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * INIT PERMISSIONS
         */
        // GROUP API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_GROUP,
                    "create group", "group", AuthoritiesConstants.CREATE_GROUP);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.ADD_PERMISSION_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.ADD_PERMISSION_GROUP,
                    "add permission to group", "group", AuthoritiesConstants.ADD_PERMISSION_GROUP);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_PERMISSION_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_PERMISSION_GROUP,
                    "delete permission of group", "group", AuthoritiesConstants.DELETE_PERMISSION_GROUP);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_GROUP,
                    "get list group", "group", AuthoritiesConstants.GET_LIST_GROUP);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_GROUP,
                    "get group", "group", AuthoritiesConstants.GET_GROUP);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_GROUP,
                    "update group", "group", AuthoritiesConstants.UPDATE_GROUP);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_GROUP)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_GROUP,
                    "delete group", "group", AuthoritiesConstants.DELETE_GROUP);
            permissionRepository.save(permission);
        }

        // PERMISSION API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_PERMISSION)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_PERMISSION,
                    "create permission", "permission", AuthoritiesConstants.CREATE_PERMISSION);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_PERMISSION)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_PERMISSION,
                    "get list permission", "permission", AuthoritiesConstants.GET_LIST_PERMISSION);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_PERMISSION)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_PERMISSION,
                    "get permission", "permission", AuthoritiesConstants.GET_PERMISSION);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_PERMISSION)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_PERMISSION,
                    "update permission", "permission", AuthoritiesConstants.UPDATE_PERMISSION);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_PERMISSION)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_PERMISSION,
                    "delete permission", "permission", AuthoritiesConstants.DELETE_PERMISSION);
            permissionRepository.save(permission);
        }
        // MEMBER API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_ADMIN_MEMBER)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_ADMIN_MEMBER,
                    "create admin", "member", AuthoritiesConstants.CREATE_ADMIN_MEMBER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_MEMBER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_MEMBER,
                    "get list member", "member", AuthoritiesConstants.GET_LIST_MEMBER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_MEMBER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_MEMBER,
                    "get member", "member", AuthoritiesConstants.GET_MEMBER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_MEMBER)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_MEMBER,
                    "update member", "member", AuthoritiesConstants.UPDATE_MEMBER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_PROFILE_MEMBER)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_PROFILE_MEMBER,
                    "update profile member", "member", AuthoritiesConstants.UPDATE_PROFILE_MEMBER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_MEMBER)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_MEMBER,
                    "delete member", "member", AuthoritiesConstants.DELETE_MEMBER);
            permissionRepository.save(permission);
        }
        // CONSUMER API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_CONSUMER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_CONSUMER,
                    "get list consumer", "consumer", AuthoritiesConstants.GET_LIST_CONSUMER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_PROFILE_CONSUMER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_PROFILE_CONSUMER,
                    "get profile consumer", "consumer", AuthoritiesConstants.GET_PROFILE_CONSUMER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_PROFILE_CONSUMER)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_PROFILE_CONSUMER,
                    "update profile consumer", "consumer", AuthoritiesConstants.UPDATE_PROFILE_CONSUMER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_CONSUMER)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_CONSUMER,
                    "delete consumer", "consumer", AuthoritiesConstants.DELETE_CONSUMER);
            permissionRepository.save(permission);
        }
        // MANAGER API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_MANAGER)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_MANAGER,
                    "create manager", "manager", AuthoritiesConstants.CREATE_MANAGER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_MANAGER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_MANAGER,
                    "get list manager", "manager", AuthoritiesConstants.GET_LIST_MANAGER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_MANAGER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_MANAGER,
                    "get list manager", "manager", AuthoritiesConstants.GET_LIST_MANAGER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_MANAGER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_MANAGER,
                    "get manager", "manager", AuthoritiesConstants.GET_MANAGER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_MANAGER)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_MANAGER,
                    "update manager", "manager", AuthoritiesConstants.UPDATE_MANAGER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_MANAGER)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_MANAGER,
                    "delete manager", "manager", AuthoritiesConstants.DELETE_MANAGER);
            permissionRepository.save(permission);
        }

        // ADDRESS API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_ADDRESS)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_ADDRESS,
                    "create address", "address", AuthoritiesConstants.CREATE_ADDRESS);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_ADDRESS)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_ADDRESS,
                    "get list address", "address", AuthoritiesConstants.GET_LIST_ADDRESS);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_ADDRESS)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_ADDRESS,
                    "get list address", "address", AuthoritiesConstants.GET_LIST_ADDRESS);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_ADDRESS)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_ADDRESS,
                    "get address", "address", AuthoritiesConstants.GET_ADDRESS);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_ADDRESS)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_ADDRESS,
                    "update address", "address", AuthoritiesConstants.UPDATE_ADDRESS);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_ADDRESS)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_ADDRESS,
                    "delete address", "address", AuthoritiesConstants.DELETE_ADDRESS);
            permissionRepository.save(permission);
        }

        // FOOD API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_FOOD)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_FOOD,
                    "create food", "food", AuthoritiesConstants.CREATE_FOOD);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_FOOD)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_FOOD,
                    "get list food", "food", AuthoritiesConstants.GET_LIST_FOOD);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_FOOD)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_FOOD,
                    "get list food", "food", AuthoritiesConstants.GET_LIST_FOOD);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_FOOD)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_FOOD,
                    "get food", "food", AuthoritiesConstants.GET_FOOD);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_FOOD)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_FOOD,
                    "update food", "food", AuthoritiesConstants.UPDATE_FOOD);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_FOOD)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_FOOD,
                    "delete food", "food", AuthoritiesConstants.DELETE_FOOD);
            permissionRepository.save(permission);
        }

        // ORDERITEM API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_ORDER_ITEM)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_ORDER_ITEM,
                    "create orderItem", "orderItem", AuthoritiesConstants.CREATE_ORDER_ITEM);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_ORDER_ITEM)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_ORDER_ITEM,
                    "get list orderItem", "orderItem", AuthoritiesConstants.GET_LIST_ORDER_ITEM);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_ORDER_ITEM)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_ORDER_ITEM,
                    "get orderItem", "orderItem", AuthoritiesConstants.GET_ORDER_ITEM);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_ORDER_ITEM)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_ORDER_ITEM,
                    "update orderItem", "orderItem", AuthoritiesConstants.UPDATE_ORDER_ITEM);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_ORDER_ITEM)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_ORDER_ITEM,
                    "delete orderItem", "orderItem", AuthoritiesConstants.DELETE_ORDER_ITEM);
            permissionRepository.save(permission);
        }
        // ORDER API
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_ORDER)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_ORDER,
                    "create order", "order", AuthoritiesConstants.CREATE_ORDER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_ORDER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_ORDER,
                    "get list order", "order", AuthoritiesConstants.GET_LIST_ORDER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_ORDER)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_ORDER,
                    "get order", "order", AuthoritiesConstants.GET_ORDER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_ORDER)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_ORDER,
                    "update order", "order", AuthoritiesConstants.UPDATE_ORDER);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_ORDER)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_ORDER,
                    "delete order", "order", AuthoritiesConstants.DELETE_ORDER);
            permissionRepository.save(permission);

        }
        // API cart
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_CART)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_CART,
                    "create cart", "cart", AuthoritiesConstants.CREATE_CART);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_CART)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_CART,
                    "get list cart", "cart", AuthoritiesConstants.GET_LIST_CART);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_CART)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_CART,
                    "get cart", "cart", AuthoritiesConstants.GET_CART);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_CART)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_CART,
                    "update cart", "cart", AuthoritiesConstants.UPDATE_CART);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_CART)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_CART,
                    "delete cart", "cart", AuthoritiesConstants.DELETE_CART);
            permissionRepository.save(permission);

        }
        // API rating
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_RATING)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_RATING,
                    "create rating", "rating", AuthoritiesConstants.CREATE_RATING);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_RATING)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_RATING,
                    "get list rating", "rating", AuthoritiesConstants.GET_LIST_RATING);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_RATING)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_RATING,
                    "get rating", "rating", AuthoritiesConstants.GET_RATING);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_RATING)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_RATING,
                    "update rating", "rating", AuthoritiesConstants.UPDATE_RATING);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_RATING)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_RATING,
                    "delete rating", "rating", AuthoritiesConstants.DELETE_RATING);
            permissionRepository.save(permission);

        }
        // API category
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_CATEGORY)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_CATEGORY,
                    "create category", "category", AuthoritiesConstants.CREATE_CATEGORY);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_CATEGORY)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_CATEGORY,
                    "get list category", "category", AuthoritiesConstants.GET_LIST_CATEGORY);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_CATEGORY)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_CATEGORY,
                    "get category", "category", AuthoritiesConstants.GET_CATEGORY);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_CATEGORY)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_CATEGORY,
                    "update category", "category", AuthoritiesConstants.UPDATE_CATEGORY);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_CATEGORY)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_CATEGORY,
                    "delete category", "category", AuthoritiesConstants.DELETE_CATEGORY);
            permissionRepository.save(permission);

        }
        // API payment
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_PAYMENT)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_PAYMENT,
                    "create payment", "payment", AuthoritiesConstants.CREATE_PAYMENT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_PAYMENT)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_PAYMENT,
                    "get list payment", "payment", AuthoritiesConstants.GET_LIST_PAYMENT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_PAYMENT)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_PAYMENT,
                    "get payment", "payment", AuthoritiesConstants.GET_PAYMENT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_PAYMENT)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_PAYMENT,
                    "update payment", "payment", AuthoritiesConstants.UPDATE_PAYMENT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_PAYMENT)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_PAYMENT,
                    "delete payment", "payment", AuthoritiesConstants.DELETE_PAYMENT);
            permissionRepository.save(permission);

        }
        // API wishlist
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_WISH_LIST)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_WISH_LIST,
                    "create wishlist", "wishlist", AuthoritiesConstants.CREATE_WISH_LIST);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_WISH_LIST)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_WISH_LIST,
                    "get list wishlist", "wishlist", AuthoritiesConstants.GET_LIST_WISH_LIST);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_WISH_LIST)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_WISH_LIST,
                    "get wishlist", "wishlist", AuthoritiesConstants.GET_WISH_LIST);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_WISH_LIST)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_WISH_LIST,
                    "update wishlist", "wishlist", AuthoritiesConstants.UPDATE_WISH_LIST);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_WISH_LIST)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_WISH_LIST,
                    "delete wishlist", "wishlist", AuthoritiesConstants.DELETE_WISH_LIST);
            permissionRepository.save(permission);

        }
        // API restaurant
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_RESTAURANT)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_RESTAURANT,
                    "create restaurant", "restaurant", AuthoritiesConstants.CREATE_RESTAURANT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_RESTAURANT)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_RESTAURANT,
                    "get list restaurant", "restaurant", AuthoritiesConstants.GET_LIST_RESTAURANT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_RESTAURANT)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_RESTAURANT,
                    "get restaurant", "restaurant", AuthoritiesConstants.GET_RESTAURANT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_RESTAURANT)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_RESTAURANT,
                    "update restaurant", "restaurant", AuthoritiesConstants.UPDATE_RESTAURANT);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_RESTAURANT)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_RESTAURANT,
                    "delete restaurant", "restaurant", AuthoritiesConstants.DELETE_RESTAURANT);
            permissionRepository.save(permission);

        }
        // API promote
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.CREATE_PROMOTE)) {
            Permission permission = new Permission(AuthoritiesConstants.CREATE_PROMOTE,
                    "create promote", "promote", AuthoritiesConstants.CREATE_PROMOTE);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_LIST_PROMOTE)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_LIST_PROMOTE,
                    "get list promote", "promote", AuthoritiesConstants.GET_LIST_PROMOTE);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.GET_PROMOTE)) {
            Permission permission = new Permission(AuthoritiesConstants.GET_PROMOTE,
                    "get promote", "promote", AuthoritiesConstants.GET_PROMOTE);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.UPDATE_PROMOTE)) {
            Permission permission = new Permission(AuthoritiesConstants.UPDATE_PROMOTE,
                    "update promote", "promote", AuthoritiesConstants.UPDATE_PROMOTE);
            permissionRepository.save(permission);
        }
        if (!permissionRepository.existsByPCode(AuthoritiesConstants.DELETE_PROMOTE)) {
            Permission permission = new Permission(AuthoritiesConstants.DELETE_PROMOTE,
                    "delete promote", "promote", AuthoritiesConstants.DELETE_PROMOTE);
            permissionRepository.save(permission);

        }
        /**
         * init 3 group
         * 1 ADMIN
         * 2 MANAGER
         * 3 CONSUMER
         * //
         */
        if (!groupRepository.existsByKind(UserBaseConstant.USER_KIND_ADMIN)) {
            Group group = new Group();
            group.setName("GROUP_ADMIN");
            group.setKind(UserBaseConstant.USER_KIND_ADMIN);
            group.setDescription("GROUP_ADMIN");
            group.setPermissions(null);
            groupRepository.save(group);
        }
        if (!groupRepository.existsByKind(UserBaseConstant.USER_KIND_MANAGER)) {
            Group group = new Group();
            group.setName("GROUP_MANAGER");
            group.setKind(UserBaseConstant.USER_KIND_MANAGER);
            group.setDescription("GROUP_MANAGER");
            group.setPermissions(null);
            groupRepository.save(group);
        }
        if (!groupRepository.existsByKind(UserBaseConstant.USER_KIND_CONSUMER)) {
            Group group = new Group();
            group.setName("GROUP_CONSUMER");
            group.setKind(UserBaseConstant.USER_KIND_CONSUMER);
            group.setDescription("GROUP_CONSUMER");
            group.setPermissions(null);
            groupRepository.save(group);
        }


        /**
         * INIT PERMISSION FOR GROUP
         */

        /**
         * ADMIN - full authority
         */
        if (groupRepository.existsByKind(UserBaseConstant.USER_KIND_ADMIN)) {
            Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_ADMIN);
            for (String permissionCode : AuthoritiesConstants.getAllPermissions()) {
                if (!groupRepository.existPermissionOfGroup(permissionCode, group.getId())) {
                    Permission permission = permissionRepository.findByPCode(permissionCode);
                    group.getPermissions().add(permission);
                }
            }
            groupRepository.save(group);
        }
        /**
         * CONSUMER - authority
         */
        if (groupRepository.existsByKind(UserBaseConstant.USER_KIND_CONSUMER)) {
            Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_CONSUMER);
            for (String permissionCode : AuthoritiesConstants.getConsumerPermissions()) {
                if (!groupRepository.existPermissionOfGroup(permissionCode, group.getId())) {
                    Permission permission = permissionRepository.findByPCode(permissionCode);
                    group.getPermissions().add(permission);
                }
            }
            groupRepository.save(group);
        }
        /**
         * MANAGER - authority
         */
        if (groupRepository.existsByKind(UserBaseConstant.USER_KIND_MANAGER)) {
            Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_MANAGER);
            for (String permissionCode : AuthoritiesConstants.getConsumerPermissions()) {
                if (!groupRepository.existPermissionOfGroup(permissionCode, group.getId())) {
                    Permission permission = permissionRepository.findByPCode(permissionCode);
                    group.getPermissions().add(permission);
                }
            }
            groupRepository.save(group);
        }
        /**
         * Create a example address admin
         */
        if (!addressRepository.existsByHouseNumber("D1")) {
            Address address = new Address();
            address.setHouseNumber("D1");
            address.setCity("Vung Tau");
            address.setCountry("VietNam");
            address.setDistrict("Ba Ria Vung Tau");
            address.setStreet("Nguyen Huu Canh");
            addressRepository.save(address);
        }
        /**
         * create member admin
         */
        if (!memberRepository.existsByKind(UserBaseConstant.USER_KIND_ADMIN)) {
            Member member = new Member();
            member.setPhoneNumber("0908048179");
            member.setIsFemale(UserBaseConstant.IS_MALE);
            member.setEmail("congnguyenhuu2703@gmail.com");
            member.setFirstName("cong");
            member.setLastName("nguyen huu");
            member.setKind(UserBaseConstant.USER_KIND_ADMIN);
            member.setImagePath("cong123.jpg");
            member.setBirthDate(new Date(2003, 11, 9, 0, 0, 0));
            member.setLogin("cong123");
            member.setHashPassword(passwordEncoder.encode("cong123654"));

            Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_ADMIN);
            member.setGroup(group);

            Address address = addressRepository.findByHouseNumber("D1");
            member.getLocals().add(address);

            memberRepository.save(member);
        }
        /**
         * Create a example address consumer
         */
        if (!addressRepository.existsByHouseNumber("D2")) {
            Address address = new Address();
            address.setHouseNumber("D2");
            address.setCity("Vung Tau");
            address.setCountry("VietNam");
            address.setDistrict("Ba Ria Vung Tau");
            address.setStreet("Nguyen Huu Canh");
            addressRepository.save(address);
        }
        /**
         * create member consumer
         */
        if (!memberRepository.existsByKind(UserBaseConstant.USER_KIND_CONSUMER)) {
            if(!memberRepository.existsByLogin("dat123")) {
                Member member = new Member();
                member.setPhoneNumber("0916818079");
                member.setIsFemale(UserBaseConstant.IS_MALE);
                member.setEmail("datnguyenhuu2703@gmail.com");
                member.setFirstName("dat");
                member.setLastName("nguyen huu");
                member.setKind(UserBaseConstant.USER_KIND_CONSUMER);
                member.setImagePath("dat123.jpg");
                member.setBirthDate(new Date(2008, 3, 23, 0, 0, 0));
                member.setLogin("dat123");
                member.setHashPassword(passwordEncoder.encode("dat123654"));

                Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_CONSUMER);
                member.setGroup(group);

                Address address = addressRepository.findByHouseNumber("D2");
                member.getLocals().add(address);

                memberRepository.save(member);
            }

            Member existMember = memberRepository.findMemberByLogin("dat123").orElse(null);
            Consumer consumer = new Consumer();
            consumer.setMember(existMember);
            consumer.setBalanceWallet(1000000L);

            consumerRepository.save(consumer);

        }
        /**
         * Create a example address manager
         */
        if (!addressRepository.existsByHouseNumber("D3")) {
            Address address = new Address();
            address.setHouseNumber("D3");
            address.setCity("Vung Tau");
            address.setCountry("VietNam");
            address.setDistrict("Ba Ria Vung Tau");
            address.setStreet("Nguyen Huu Canh");
            addressRepository.save(address);
        }
        /**
         * create manager
         */
        if (!memberRepository.existsByKind(UserBaseConstant.USER_KIND_MANAGER)) {
            Member member = new Member();
            member.setPhoneNumber("0913618079");
            member.setIsFemale(UserBaseConstant.IS_MALE);
            member.setEmail("quannguyenhuu2703@gmail.com");
            member.setFirstName("quan");
            member.setLastName("nguyen huu");
            member.setKind(UserBaseConstant.USER_KIND_MANAGER);
            member.setImagePath("quan123.jpg");
            member.setBirthDate(new Date(2008, 3, 23, 0, 0, 0));
            member.setLogin("quan123");
            member.setHashPassword(passwordEncoder.encode("quan123654"));

            Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_MANAGER);
            member.setGroup(group);

            Address address = addressRepository.findByHouseNumber("D3");
            member.getLocals().add(address);

            memberRepository.save(member);

            Manager manager = new Manager();
            manager.setMember(member);
            manager.setOwnerRestaurant(null);

            managerRepository.save(manager);

        }

    }
}
