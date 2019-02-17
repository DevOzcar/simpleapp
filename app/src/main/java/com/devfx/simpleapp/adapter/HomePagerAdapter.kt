package com.devfx.simpleapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.devfx.simpleapp.models.InfoFragment

class HomePagerAdapter( private val lstFragments: ArrayList<InfoFragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return lstFragments[position].fragment
    }

    override fun getCount(): Int {
        return lstFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return lstFragments[position].title
    }
}