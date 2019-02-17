package com.devfx.simpleapp.restful

import com.devfx.simpleapp.models.Credential
import com.devfx.simpleapp.models.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RestApi {

    //El endpoint no soporta parametros, por lo que no se puede implementar @Field :S"
    @POST("test")
    fun login(@Body body: Credential): Call<ResponseLogin>
}