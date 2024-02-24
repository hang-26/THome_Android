package com.example.thome.start.data

import android.provider.BaseColumns

object UserContractClass {

        object UserEntry {
            const val TABLE_NAME = "user"
            const val COLUMN_ID = "id"
            const val COLUMN_USERNAME = "username"
            const val COLUMN_PASSWORD = "password"
            const val COLUMN_USER_TYPE = "usertype"
        }

}