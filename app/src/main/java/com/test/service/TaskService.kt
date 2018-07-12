package com.test.service

import com.test.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TaskService {
    private var retrofit: Retrofit? = null


    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    val api: TaskAPI
        get() {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return retrofit!!.create(TaskAPI::class.java)
        }
}
