package com.test.ui.view

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.test.R
import com.test.presenter.TaskPresenter
import com.test.ui.adapter.IndicatorAdaptor
import com.test.ui.adapter.SurveyAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskView {

    var taskPresenter: TaskPresenter? = null
    private var adapter: SurveyAdapter? = null
    private var indicatorAdaptor: IndicatorAdaptor? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskPresenter = TaskPresenter(this)

        adapter = SurveyAdapter(this, taskPresenter!!.getSurveyList())
        activity_main_survey_vp.adapter = adapter
        attachIndicator()

        activity_main_refresh_iv.setOnClickListener { taskPresenter!!.refreshData() }
    }

    /*
    * Setting IndicatorAdapter to activity_main_survey_indicator_rv
    * Performing IndicatorAdapter update on activity_main_survey_vp page change
    * */
    private fun attachIndicator() {
        indicatorAdaptor = IndicatorAdaptor(this, taskPresenter!!.getSurveyList())
        activity_main_survey_indicator_rv.adapter = indicatorAdaptor
        val layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        activity_main_survey_indicator_rv.layoutManager = layoutManager

        activity_main_survey_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                taskPresenter?.scrollIndicatorView(position, layoutManager.findFirstCompletelyVisibleItemPosition(), layoutManager.findLastCompletelyVisibleItemPosition())
                taskPresenter?.notifyPageChanged(position)
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    /*
    * Setting Visibility of progress bar
    * @param - loaderVisibility
    *
    * */
    override fun showLoader(loaderVisibility: Boolean) {
        if (loaderVisibility)
            activity_main_survey_progress.visibility = View.VISIBLE
        else
            activity_main_survey_progress.visibility = View.GONE
    }

    /*
    * Notifying SurveyAdapter and IndicatorAdaptor of items changed, after fetching result from RemoteRepository
    * */
    override fun updateSurveyList() {
        adapter?.notifyDataSetChanged()
        activity_main_survey_vp.adapter = adapter // hack to clear viewpager data on refresh
        indicatorAdaptor?.notifyDataSetChanged()
    }

    /*
    * Updating indicator position according to activity_main_survey_vp page change
    * @param - pagePosition
    * */
    override fun updateIndicator(pagePosition: Int) {
        indicatorAdaptor?.notifyItemChanged(pagePosition)
    }

    /*
    * Auto Scrolling activity_main_survey_indicator_rv to new position
    * @param - position
    * */
    override fun scrollIndicatorView(position: Int) {
        activity_main_survey_indicator_rv.scrollToPosition(position)
    }

    /*
    * Preforming last task before onDestroy
    * */
    override fun onDestroy() {
        taskPresenter?.onDestroy()
        super.onDestroy()
    }

    /*
    * Showing error if unable to fetch response
    * @param - error
    * */
    override fun showResponseError(error: Throwable?) {
        Toast.makeText(this, error?.localizedMessage
                ?: "Please try again.", Toast.LENGTH_SHORT).show();
    }
}