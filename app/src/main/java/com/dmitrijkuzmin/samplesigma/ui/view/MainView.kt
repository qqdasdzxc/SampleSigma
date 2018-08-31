package com.dmitrijkuzmin.samplesigma.ui.view

import com.dmitrijkuzmin.samplesigma.model.entities.Rss

interface MainView {
//    fun onStartLoading()
//    fun onFinishLoading()
    fun showProgress()
    fun hideProgress()
    fun setItems(result: Rss)
    fun showError(message: String)
}