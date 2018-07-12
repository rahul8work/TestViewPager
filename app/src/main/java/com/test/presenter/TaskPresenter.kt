package com.test.presenter

import android.util.Log
import com.test.service.RemoteRepository
import com.test.service.model.SurveyModel
import com.test.ui.view.TaskView
import io.reactivex.disposables.CompositeDisposable


class TaskPresenter(private val taskView: TaskView) {

    private var remoteRepository: RemoteRepository? = null
    private var compositeDisposable = CompositeDisposable()
    private val list = ArrayList<SurveyModel>()
    private var pagePosition = 0


    /*
    * Performing fetch response on init TaskPresenter
    * */
    init {
        if (this.remoteRepository == null) {
            this.remoteRepository = RemoteRepository()
            processSurveyResponse()
        }
    }

    /*
    * Processing data fetched from RemoteRepository.performFetchSurveys
    * Updating loaders visibility and updating survey list
    * binding disposable to compositeDisposable
    * */
    private fun processSurveyResponse() {
        val observable = remoteRepository!!.performFetchSurveys()
        observable?.subscribe(
                {
                    list.addAll(it)
                    taskView.showLoader(false)
                    taskView.updateSurveyList()
                },
                {
                    taskView.showResponseError(it);
                    Log.e("TAG", "{$it.message}")
                },
                {
                    Log.e("TAG", "performFetchSurveys completed")
                },
                {
                    compositeDisposable.add(it)
                }
        )
    }

    /*
    * Disposing observable on Activity Destroyed
    * */
    fun onDestroy() {
        compositeDisposable.dispose()
    }

    /*
    * Returning list of SurveyModel
    * @return - list
    * */
    fun getSurveyList(): List<SurveyModel> {
        return list
    }

    /*
    * Clearing data on refresh and updating Adapters
    * Fetching data from RemoteRepository and Showing loader to show progress
    * */
    fun refreshData() {
        list.clear()
        taskView.updateSurveyList()
        taskView.showLoader(true)
        processSurveyResponse()
    }

    /*
    * Notifying Indicator with updated @variable - list data
    * @param - position
    * */
    fun notifyPageChanged(position: Int) {
        list[pagePosition].isShown = false
        taskView.updateIndicator(pagePosition)

        pagePosition = position
        list[pagePosition].isShown = true
        taskView.updateIndicator(pagePosition)
    }

    /*
    * Determining if current page position is lastCompletelyVisibleItemPosition or firstCompletelyVisibleItemPosition
    * If true scrolling IndicatorView to next position
    * @param - nextPosition
    * @param - lastCompletelyVisibleItemPosition
    * @param - firstCompletelyVisibleItemPosition
    * */
    fun scrollIndicatorView(nextPosition: Int, firstCompletelyVisibleItemPosition: Int, lastCompletelyVisibleItemPosition: Int) {
        if (nextPosition == lastCompletelyVisibleItemPosition + 1 || nextPosition == firstCompletelyVisibleItemPosition - 1)
            taskView.scrollIndicatorView(nextPosition)
    }

}
