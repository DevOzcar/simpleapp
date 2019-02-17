package com.devfx.simpleapp.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Contacts : RealmObject() {

    @JvmField
    var name: String? = null

    @JvmField
    var lastname: String? = null

    @JvmField
    var age: String? = null

    @PrimaryKey
    @JvmField
    var phone: String? = null

    @JvmField
    var image: String? = null

}