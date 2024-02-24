package com.example.thome.start.data.user

class UserData {
    var userId: Long = 0;

    var userName: String = "";
    var userPass: String = "";
    var userType: Int = 0;
    constructor(userName: String, userPass: String, userType: Int) {
        this.userName = userName
        this.userPass = userPass
        this.userType = userType

    }
}