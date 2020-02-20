package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.*;

public class UsersUtil {
//    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<User> USERS = Arrays.asList(
            new User(null, "Tohas", "korn@ukr.net", "131313", Role.ROLE_USER),
            new User(null, "Nata", "nata@email.ru", "131313", Role.ROLE_USER),
            new User(null, "Aleksey", "aleks@email.ru", "131313", Role.ROLE_USER),
            new User(null, "Dimon", "dima@dmyhrik.com.ua", "131313", Role.ROLE_USER)
    );

}
