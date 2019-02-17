package com.devfx.simpleapp.contact.add

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.devfx.simpleapp.R
import com.devfx.simpleapp.databinding.FragmentAddcontactBinding
import com.devfx.simpleapp.models.Contacts
import com.devfx.simpleapp.util.GenericConverter
import com.devfx.simpleapp.util.ImagePicker
import com.vicpin.krealmextensions.save
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_addcontact.view.*


class AddFragment : Fragment() {

    var contacts: Contacts = Contacts()
    lateinit var btnSave: Button
    lateinit var circleAvatar: CircleImageView
    lateinit var binding: FragmentAddcontactBinding

    var permissions = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bitmap = ImagePicker.getImageFromResult(activity, resultCode, data)
        circleAvatar.setImageBitmap(bitmap)
        contacts.image = GenericConverter.getBase64(bitmap)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                val intent = ImagePicker.getPickImageIntent(activity)
                startActivityForResult(intent, 999)
            }
            return
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddcontactBinding.inflate(inflater!!, container, false)
        binding.contacts = contacts

        btnSave = binding.root.findViewById(R.id.btnSave)
        circleAvatar = binding.root.findViewById(R.id.imgAvatar)

        circleAvatar.setOnClickListener { showPicker() }
        btnSave.setOnClickListener { onSaveContact() }

        return binding.root
    }

    fun onSaveContact() {
        contacts.name = binding.root.edtName.text.toString()
        contacts.age = binding.root.edtAge.text.toString()
        contacts.lastname = binding.root.edtLastName.text.toString()
        contacts.phone = binding.root.edtPhone.text.toString()

        if (contacts.isValid)
            contacts.save()

    }

    fun checkPermissions(): Boolean {
        var result: Int
        val listPermissionsNeeded = arrayListOf<String>()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(this.activity!!, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this.activity!!, listPermissionsNeeded.toTypedArray(), 100)
            return false
        }
        return true
    }


    fun showPicker() {
        if (checkPermissions()) {
            val intent = ImagePicker.getPickImageIntent(this.activity)
            startActivityForResult(intent, 999)
        }
    }
}