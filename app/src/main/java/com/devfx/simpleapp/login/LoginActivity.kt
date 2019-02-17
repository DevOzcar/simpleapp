@file:Suppress("UNREACHABLE_CODE")

package com.devfx.simpleapp.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.devfx.simpleapp.R
import com.devfx.simpleapp.home.HomeActivity
import com.devfx.simpleapp.models.Contacts
import com.devfx.simpleapp.models.Credential
import com.vicpin.krealmextensions.deleteAll

class LoginActivity : AppCompatActivity(), LoginContract.View, TextWatcher {

    private val TAG: String = "LoginActivity"
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var btnLogin: Button
    private lateinit var edtUser: EditText
    private lateinit var edtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = LoginPresenter(this)

        initComponets()

        //for dummy info
        resetData()
    }

    fun initComponets() {
        btnLogin = findViewById(R.id.btnLogin)
        edtUser = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)

        edtUser.addTextChangedListener(this)
        edtPassword.addTextChangedListener(this)
    }

    fun blockComponents(value: Boolean) {
        edtUser.isEnabled = value
        edtPassword.isEnabled = value
        btnLogin.isEnabled = value
    }

    fun onStartLogin(view: View) {
        blockComponents(false)
        presenter.onLogin(Credential(edtUser.text.toString(), edtPassword.text.toString()))
    }

    override fun succesLogin() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun showToast(message: String) {
        blockComponents(true)
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun afterTextChanged(s: Editable?) {
        btnLogin.isEnabled = !edtUser.text.trim().isEmpty() && !edtPassword.text.trim().isEmpty()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d(TAG, "beforeTextChanged")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d(TAG, "beforeTextChanged")
    }


    fun resetData(){
        presenter.resetData()
    }
}
