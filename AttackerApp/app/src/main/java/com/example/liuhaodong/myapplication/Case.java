package com.example.liuhaodong.myapplication;

//import com.google.auto.value.AutoValue;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liuhaodong1 on 11/17/17.
 */
//
//@AutoValue
//abstract class Case{
//
//    static Case create(String name, String description, String data, String resp) {
//        return new AutoValue_Case(name, description, data, resp);
//    }
//
//    public abstract String name();
//    public abstract String description();
//    public abstract String data();
//    public abstract String resp();
//}

abstract class Case{

    public abstract String name();
    public abstract String description();
    public abstract String data();
}