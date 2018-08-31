package com.dmitrijkuzmin.samplesigma.ui.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dmitrijkuzmin.samplesigma.R
import com.dmitrijkuzmin.samplesigma.model.entities.Rss
import com.dmitrijkuzmin.samplesigma.ui.presenter.MainPresenter
import com.dmitrijkuzmin.samplesigma.ui.presenter.MainPresenterImpl

class MainFragment : Fragment(), MainView {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recycler: RecyclerView
    lateinit var adapter: MainAdapter
    lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        if (savedInstanceState == null) {
            adapter = MainAdapter()
            initRecyclerView(view)
            presenter = MainPresenterImpl(this)
        } else {
            initRecyclerView(view)
        }

        return view
    }

    private fun initRecyclerView(view: View) {
        recycler = view.findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(context)

        recycler.adapter = adapter

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener({
            presenter.loadData()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

//    override fun onStartLoading() {
//        swipeRefreshLayout.isEnabled = false
//    }
//
//    override fun onFinishLoading() {
//        swipeRefreshLayout.isEnabled = true
//    }

    override fun showProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun setItems(result: Rss) {
        adapter.setItems(result.channel.items)
    }

    override fun showError(message: String) {
        Toast.makeText(context, "Connection error. Try later.", Toast.LENGTH_LONG).show()
    }
}