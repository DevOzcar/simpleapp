package com.devfx.simpleapp.login

import com.devfx.simpleapp.models.Credential
import com.devfx.simpleapp.models.ResponseLogin
import com.devfx.simpleapp.models.Session
import com.vicpin.krealmextensions.save


class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    private var model: LoginModel = LoginModel(this)

    override fun onLogin(credentials: Credential) {
        model.requestLogin(credentials)
    }

    override fun onError(message: String) {
        view.showToast(message)
    }

    override fun onSuccess(responseLogin: ResponseLogin?) {
        if (isValidCode(responseLogin?.statusCode!!))
            storageSession(responseLogin.body?.auth?.accessToken!!, responseLogin.body?.auth?.user!!)
        else
            view.showToast(responseLogin.errorBody!!)
    }

    private fun storageSession(token: String, data: ResponseLogin.User ) {

        //A example storage with krealmextensions o.O
        val session = Session()
        session.accessToken = token
        session.name = data.name
        session.email = data.email
        session.address = data.address
        session.lastName = data.lastName
        session.save()

        view.succesLogin()
    }

    fun isValidCode(code: Int): Boolean {
        return code == 200
    }

    override fun resetData() {
        model.resetToZero()
    }
}