package com.dmitrijkuzmin.samplesigma.ui.presenter

import com.dmitrijkuzmin.samplesigma.business.MainInteractor
import com.dmitrijkuzmin.samplesigma.business.MainInteractorImpl
import com.dmitrijkuzmin.samplesigma.model.entities.Rss
import com.dmitrijkuzmin.samplesigma.ui.view.MainView

class MainPresenterImpl(var view: MainView) : MainPresenter {

    private var interactor: MainInteractor = MainInteractorImpl(this)

    init {
        loadData()
    }

    override fun loadData() {
        //view.onStartLoading()
        view.showProgress()
        interactor.loadData()
    }

    override fun onLoadingSuccess(result: Rss) {
        view.setItems(result)
        view.hideProgress()
        //view.onFinishLoading()
    }

    override fun onLoadingError(message: String) {
        view.showError(message)
        view.hideProgress()
        //view.onFinishLoading()
    }
}