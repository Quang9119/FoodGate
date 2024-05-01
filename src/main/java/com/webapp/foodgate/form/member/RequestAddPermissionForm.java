package com.webapp.foodgate.form.member;

import lombok.Data;

@Data
public class RequestAddPermissionForm {
    private String name;
    private String description;
    private String permissionCode;
    private String nameGroup;
}
