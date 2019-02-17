package com.devfx.simpleapp.login

import com.devfx.simpleapp.models.Credential
import com.devfx.simpleapp.models.ResponseLogin


interface LoginContract {
    interface View {
        fun succesLogin()
        fun showToast(message: String)
    }

    interface Presenter {
        fun onLogin(credentials: Credential)
        fun onError(message: String)
        fun onSuccess(responseLogin: ResponseLogin?)
        fun resetData()
    }

    interface Model {
        fun requestLogin(credentials: Credential)
        fun resetToZero()
    }
}