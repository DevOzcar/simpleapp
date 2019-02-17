package com.devfx.simpleapp.login

import com.devfx.simpleapp.models.Contacts
import com.devfx.simpleapp.models.Credential
import com.devfx.simpleapp.models.ResponseLogin
import com.devfx.simpleapp.models.Session
import com.devfx.simpleapp.restful.DefaultClient
import com.vicpin.krealmextensions.deleteAll
import com.vicpin.krealmextensions.save
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel(val presenter: LoginContract.Presenter) : LoginContract.Model {

    override fun requestLogin(credentials: Credential) {

        val call = DefaultClient.provideApiService().login(credentials)

        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {

                // siempre regresan 200 ? la respuestas deberia cambiar con el code status
                if (response.isSuccessful )
                    presenter.onSuccess(response.body())
                else
                    presenter.onError(response.errorBody().toString())
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                presenter.onError(t.message.toString())
            }
        })
    }

    override fun resetToZero() {
        Session().deleteAll()
        Contacts().deleteAll()

        var i = 0
        while (i < 10){

            var dummyObj: Contacts = Contacts()
            dummyObj.name = "Dummy $i"
            dummyObj.lastname = "Dummy last$i"
            dummyObj.age = "2$i"
            dummyObj.phone = "3333333$i"

            dummyObj.save()

            i++
        }
    }
}