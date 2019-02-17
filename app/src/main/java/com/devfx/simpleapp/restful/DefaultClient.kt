package com.devfx.simpleapp.restful

import com.devfx.simpleapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DefaultClient {

        private fun provideClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
            return client.build()
        }

        private fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun provideApiService(): RestApi {
            return provideRetrofit().create<RestApi>(RestApi::class.java)
        }

}