package com.example.lsj.mvp.utils;

import com.example.lsj.mvp.bean.UserBean;

import java.util.HashMap;
import java.util.Map;

public class DataSet {

    private static Map<String, Integer> integerMap = new HashMap<>();
    private static Map<String, String> stringMap = new HashMap<>();
    private static Map<String, Object> objectsMap = new HashMap<>();

    private static UserBean user;

    public static void putIntData(String label, int data){
        integerMap.put(label, data);
    }

    public static void putStringData(String label, String data){
        stringMap.put(label, data);
    }

    public static void putObject(String label, Object data){
        objectsMap.put(label, data);
    }

    public static int getIntData(String label){
        return integerMap.get(label);
    }

    public static String getStringData(String label){
        return stringMap.get(label);
    }

    public static Object getObjectData(String label){
        return objectsMap.get(label);
    }

    public static void removeIntData(String label){
        integerMap.remove(label);
    }

    public static void removeStringData(String label){
        stringMap.remove(label);
    }

    public static void removeObjectData(String label){
        objectsMap.remove(label);
    }

    public static UserBean getUser() {
        return user;
    }

    public static void setUser(UserBean user) {
        DataSet.user = user;
    }
}
