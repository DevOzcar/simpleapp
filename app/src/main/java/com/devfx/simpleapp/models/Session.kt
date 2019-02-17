package com.devfx.simpleapp.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Session : RealmObject() {

    @PrimaryKey
    @JvmField
    var email: String? = null

    @JvmField
    var name: String? = null

    @JvmField
    var lastName: String? = null

    @JvmField
    var address: String? = null

    @JvmField
    var accessToken: String? = null
}