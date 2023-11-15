package com.cookandroid.login_front.DBManagement;

import android.content.Context;

import com.cookandroid.login_front.database.User;
import com.cookandroid.login_front.database.UserDatabase;

public class LoginManager {
    private final Context context;
    private final UserDatabase userDatabase;

    public LoginManager(Context context) {
        this.context = context;
        userDatabase = UserDatabase.getInstance(context);
    }

    public void login(String userId, String password, Runnable onSuccess, Runnable onFailure) {
        new Thread(() -> {
            User user = userDatabase.userDao().getUser(userId, password);
            if (user != null) {
                onSuccess.run();
            } else {
                onFailure.run();
            }
        }).start();
    }
}
