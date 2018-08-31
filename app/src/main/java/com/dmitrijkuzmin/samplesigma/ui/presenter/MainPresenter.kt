package com.dmitrijkuzmin.samplesigma.ui.presenter

import com.dmitrijkuzmin.samplesigma.model.entities.Rss

interface MainPresenter {
    fun loadData()
    fun onLoadingSuccess(result: Rss)
    fun onLoadingError(message: String)
}