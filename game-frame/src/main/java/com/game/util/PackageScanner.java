package com.game.util;


import org.reflections.Reflections;

import java.util.Set;

public class PackageScanner {


    //扫描action
    public static Set<Class<?>> scan(String packageName,Class clazz){
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(clazz);
        return classes;

    }



}
