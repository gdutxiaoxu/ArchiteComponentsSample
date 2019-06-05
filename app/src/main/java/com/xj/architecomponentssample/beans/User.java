package com.xj.architecomponentssample.beans;

/**
 * Created by jun xu on 2019-06-03.
 */
public class User {
    public String userName;
    public String uid;
    public String avatar;

    public User(String userName, String uid) {
        this.userName = userName;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", uid='" + uid + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
