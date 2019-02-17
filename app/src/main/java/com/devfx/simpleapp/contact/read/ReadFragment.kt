package com.devfx.simpleapp.contact.read

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.devfx.simpleapp.R
import com.devfx.simpleapp.adapter.ReadAdapter
import com.devfx.simpleapp.dialog.RemoveContactDialog
import com.devfx.simpleapp.models.Contacts


class ReadFragment : Fragment(), ReadAdapter.OnItemClickListener, RemoveContactDialog.OnRemoveContact, SearchView.OnQueryTextListener,
    ReadContract.View {

    private lateinit var adapter: ReadAdapter
    private lateinit var rvContacts: RecyclerView
    private var lstContacts = arrayListOf<Contacts>()
    private lateinit var presenter: ReadPresenter
    private lateinit var searchView: SearchView

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.menu_toolbar, menu)
        val search: MenuItem? = menu?.findItem(R.id.optSearch)
        searchView = search!!.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_contacts, container, false)

        setHasOptionsMenu(true)

        rvContacts = root.findViewById(R.id.rvContact)
        rvContacts.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        initAdapter()

        presenter = ReadPresenter(this)
        presenter.consultORM()

        return root
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed)
            presenter.consultORM()
    }

    override fun onItem(value: String) {
        //a custom dialog o.O
        RemoveContactDialog(activity, this, value).show()
    }

    fun initAdapter() {
        adapter = ReadAdapter(lstContacts, this)
        rvContacts.adapter = adapter
    }

    override fun onRemove(value: String) {
        presenter.deleteORM(value)
    }

    override fun loadData(contacts: List<Contacts>) {
        lstContacts.clear()
        lstContacts.addAll(contacts)
        adapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        presenter.queryORM(p0!!)
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        presenter.queryORM(p0!!)
        return true
    }
}