package com.test.service

import com.test.service.model.SurveyModel
import com.test.util.ACCESS_TOKEN
import com.test.util.PAGE
import com.test.util.PER_PAGE
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepository {

    private var taskService: TaskService? = null

    init {
        if (this.taskService == null) {
            this.taskService = TaskService()
        }
    }

    /*
    * fetching surveys with
    * @param - page = 1
    * @param - per_page = 25
    * */
    fun performFetchSurveys(): Observable<List<SurveyModel>>? {
        return taskService!!
                .api
                .fetchSurveys(ACCESS_TOKEN, PAGE, PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}