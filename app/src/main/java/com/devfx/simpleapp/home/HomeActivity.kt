package com.devfx.simpleapp.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.devfx.simpleapp.R
import com.devfx.simpleapp.adapter.HomePagerAdapter
import com.devfx.simpleapp.contact.add.AddFragment
import com.devfx.simpleapp.contact.read.ReadFragment
import com.devfx.simpleapp.models.InfoFragment
import com.devfx.simpleapp.models.Session
import com.vicpin.krealmextensions.queryFirst

class HomeActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initTitleToolbar()

        initComponents()
    }

    fun initTitleToolbar() {
        val currentSession = Session().queryFirst()
        supportActionBar?.title = currentSession?.name;
    }

    fun initComponents() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        setUpFragments()
    }

    fun setUpFragments() {

        val lstInfoFragment = arrayListOf<InfoFragment>()

        lstInfoFragment.add(InfoFragment(getString(R.string.title_contacts), ReadFragment()))
        lstInfoFragment.add(InfoFragment(getString(R.string.title_add_contact), AddFragment()))

        viewPager.adapter = HomePagerAdapter(lstInfoFragment, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}