package com.android.bottomsheetSample.subStackFragments

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView


class OnSearchPagerAdapter(
        val arrayTitle: Array<String>,
        fm: FragmentManager,
) : FragmentPagerAdapter(fm) {


    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment =  ViewpagerFragmentOne()

    override fun getPageTitle(position: Int): CharSequence {
        return arrayTitle[position]
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        val f = `object` as Fragment
        val activeFragmentTag = f.tag
        val view: View? = f.view
        if (view != null) {
            val nestedView: View = view.findViewWithTag("nested")
            if (nestedView != null && nestedView is RecyclerView) {
                (nestedView as RecyclerView).isNestedScrollingEnabled = true
            }
        }
        val fm = f.fragmentManager
        for (frag in fm!!.fragments) {
            if (frag.tag !== activeFragmentTag) {
                val v: View? = frag.view
                if (v != null) {
                    val nestedView: View = v.findViewWithTag("nested")
                    if (nestedView != null && nestedView is RecyclerView) {
                        (nestedView as RecyclerView).isNestedScrollingEnabled = false
                    }
                }
            }
        }
        container.requestLayout()
    }

}