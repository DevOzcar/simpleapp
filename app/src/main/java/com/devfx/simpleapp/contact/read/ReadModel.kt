package com.devfx.simpleapp.contact.read

import com.devfx.simpleapp.models.Contacts
import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryAll

class ReadModel(val presenter: ReadContract.Presenter) : ReadContract.Model {

    override fun deleteData(value: String) {
        Contacts().delete {
            equalTo("phone", value)
        }

        presenter.consultORM()

    }

    override fun readData() {
        presenter.deliveryData(Contacts().queryAll())
    }

    override fun queryData(value: String) {
        presenter.deliveryData(Contacts().query {
            contains("name", value)
        })
    }
}