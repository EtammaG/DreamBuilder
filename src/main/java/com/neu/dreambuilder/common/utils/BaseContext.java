package com.neu.dreambuilder.common.utils;


import com.neu.dreambuilder.entity.user.IUserDetails;

public class BaseContext {
    private static final ThreadLocal<IUserDetails> threadLocal = new ThreadLocal<>();

    public static void setCurrentIUserDetails(IUserDetails login){
        threadLocal.set(login);
    }

    public static IUserDetails getCurrentIUserDetails(){
        return threadLocal.get();
    }
}