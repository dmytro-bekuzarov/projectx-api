package com.sind.projectx.repository;

import com.sind.projectx.domain.User;

/**
 * @author Dmytro Bekuzarov
 */
public class CurrentUserHolder {

    private static final ThreadLocal<User> USER = new ThreadLocal<>();

    private static final String SYSTEM_USER_NAME = "system";
    private static final String SYSTEM_USER_LANG = "en";

    private CurrentUserHolder() {
    }

    public static void setUser(User user) {
        USER.set(user);
    }

    public static User getUser() {
        return USER.get();
    }

    public static String getUserName() {
        User user = USER.get();
        return user == null ? SYSTEM_USER_NAME : user.getEmail();
    }

    public static String getUserLang() {
        User user = USER.get();
        return user == null ? SYSTEM_USER_LANG : user.getLang();
    }

}
