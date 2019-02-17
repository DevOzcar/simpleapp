package com.devfx.simpleapp

import android.app.Application
import com.devfx.simpleapp.orm.AppMigrations
import io.realm.Realm
import io.realm.RealmConfiguration

class SimpleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config: RealmConfiguration = RealmConfiguration.Builder()
            .name("simpleapp.realm")
            .schemaVersion(BuildConfig.DB_MIGRATION)
            .migration(AppMigrations())
            .build()

        Realm.setDefaultConfiguration(config)
    }
}