package com.itheima.utils;

import com.itheima.pojo.UserContext;

public class CurrentHolder {
    private static final ThreadLocal<UserContext> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrent(UserContext user) {
        CURRENT_LOCAL.set(user);
    }

    public static UserContext getCurrent() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
