package com.test.service

import com.test.service.model.SurveyModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskAPI {

    @GET("surveys.json")
    fun fetchSurveys(@Query("access_token") access_token: String,
                     @Query("page") page: Int,
                     @Query("per_page") per_page: Int): Observable<List<SurveyModel>>

}
