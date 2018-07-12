package com.test.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.R
import com.test.service.model.SurveyModel

class IndicatorAdaptor(private val mContext: Context, private val list: List<SurveyModel>) : RecyclerView.Adapter<IndicatorAdaptor.IndicatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return IndicatorViewHolder(mInflater.inflate(R.layout.indicator_view, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        // Updating imageview drawable on which survey is visible
        if (list[position].isShown == false)
            holder.view.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.non_active_dot));
        else
            holder.view.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));
    }

    class IndicatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView
    }
}