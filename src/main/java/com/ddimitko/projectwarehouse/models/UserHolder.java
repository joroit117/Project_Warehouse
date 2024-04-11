package com.ddimitko.projectwarehouse.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserHolder {

    private User user;
    private static final UserHolder INSTANCE = new UserHolder();

    private UserHolder() {
    }

    public static UserHolder getInstance() {
        return INSTANCE;
    }

}
