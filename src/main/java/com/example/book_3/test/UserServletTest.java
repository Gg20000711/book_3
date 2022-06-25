package com.example.book_3.test;

import java.lang.reflect.Method;

/**
 * @author 高先锋好帅
 * @date 2022-06-07 12:21
 */
public class UserServletTest {

    public static String action = "regist";
    private void login(){
        System.out.println("这是login方法被调用了");
    }
    private void regist(){
        System.out.println("这是regist方法被调用了");
    }
    private void updateUsername(){
        System.out.println("这是updateUsername方法被调用了");
    }
    private void updataPassword(){
        System.out.println("这是updataPassword方法被调用了");
    }

    public static void main(String[] args) {
        try {
            Method method = UserServletTest.class.getDeclaredMethod(action);
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
