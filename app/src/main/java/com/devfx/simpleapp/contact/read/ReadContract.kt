package com.devfx.simpleapp.contact.read

import com.devfx.simpleapp.models.Contacts

interface ReadContract {

    interface View {
        fun loadData(contacts: List<Contacts>)
    }

    interface Presenter {
        fun consultORM()
        fun queryORM(value: String)
        fun deleteORM(value: String)
        fun deliveryData(contacts: List<Contacts>)
    }

    interface Model {
        fun deleteData(value: String)
        fun readData()
        fun queryData(value: String)
    }
}