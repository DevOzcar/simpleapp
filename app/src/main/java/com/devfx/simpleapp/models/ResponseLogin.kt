package com.devfx.simpleapp.models

import com.google.gson.annotations.SerializedName

class ResponseLogin {
    @SerializedName("statusCode")
    var statusCode: Int? = 0
    @SerializedName("body")
    var body: Body? = null

    //ERROR o.O - Json deformado con los escapes / regreso el mensaje tal cual
    @SerializedName("errorMessage")
    var errorBody: String? = null

    class Body {
        @SerializedName("status")
        var status: String? = null
        @SerializedName("auth")
        var auth: Auth? = null
//        @SerializedName("errorMessage")
//        var errorMessage: String? = null
    }


    class Auth {
        @SerializedName("user")
        var user: User? = null
        @SerializedName("access_token")
        var accessToken: String? = null
    }

    class User {
        @SerializedName("name")
        var name: String? = null
        @SerializedName("lastname")
        var lastName: String? = null
        @SerializedName("email")
        var email: String? = null
        @SerializedName("address")
        var address: String? = null
    }

}