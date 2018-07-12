package com.test.ui.view


interface TaskView {

    fun updateSurveyList()
    fun updateIndicator(pagePosition: Int)
    fun scrollIndicatorView(position: Int)
    fun showResponseError(error: Throwable?)
    fun showLoader(loaderVisibility: Boolean)
}
