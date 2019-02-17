package com.devfx.simpleapp.contact.read

import com.devfx.simpleapp.models.Contacts

class ReadPresenter(val view: ReadContract.View) : ReadContract.Presenter {

    val model: ReadModel = ReadModel(this)

    override fun consultORM() {
        model.readData()
    }

    override fun queryORM(value: String) {
        model.queryData(value)
    }

    override fun deleteORM(value: String) {
        model.deleteData(value)
    }

    override fun deliveryData(contacts: List<Contacts>) {
        view.loadData(contacts)
    }
}