package com.cookandroid.login_front.DBManagement;

import android.content.Context;

import com.cookandroid.login_front.database.User;
import com.cookandroid.login_front.database.UserDatabase;

public class SignupManager {
    private final Context context;
    private final UserDatabase userDatabase;

    public SignupManager(Context context) {
        this.context = context;
        userDatabase = UserDatabase.getInstance(context);
    }

    public void signup(String username, String userId, String password, Runnable onSuccess, Runnable onFailure) {
        new Thread(() -> {
            User existingUser = userDatabase.userDao().getUserById(userId);
            if (existingUser == null) {
                User newUser = new User(username, userId, password);
                userDatabase.userDao().insertUser(newUser);
                onSuccess.run();
            } else {
                onFailure.run();
            }
        }).start();
    }
}

