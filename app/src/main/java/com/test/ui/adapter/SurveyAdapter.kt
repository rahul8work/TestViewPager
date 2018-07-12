package com.test.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.test.R
import com.test.service.model.SurveyModel


class SurveyAdapter(private val mContext: Context, private val list: List<SurveyModel>) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_layout, null)

        val imageView = view.findViewById(R.id.item_layout_iv) as ImageView
        Picasso.get().load(list[position].coverImageUrl + "l").into(imageView)

        // Using SpannableString to change size of title
        val textView = view.findViewById(R.id.item_layout_tv) as TextView
        val spannableStringBuilder = SpannableString(list[position].title + "\n" + list[position].description)
        spannableStringBuilder.setSpan(RelativeSizeSpan(2.3f), 0, list[position].title?.length
                ?: 0, 0);
        textView.text = spannableStringBuilder

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}
