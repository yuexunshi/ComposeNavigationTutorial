package com.asi.composenavigationtutorial.model

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

/**
 * @ClassName UserBean.java
 * @author usopp
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年06月10日 15:59:00
 */

@Parcelize
data class UserBean(val name:String,val age:Int):Parcelable{
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}

class UserNavType : NavType<UserBean>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UserBean? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): UserBean {
        return Gson().fromJson(value, UserBean::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: UserBean) {
        bundle.putParcelable(key, value)
    }
}