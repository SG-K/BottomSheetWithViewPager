package com.android.bottomsheetSample.subStackFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.android.bottomsheetSample.DataManagement
import com.android.bottomsheetSample.databinding.FragmentOnSearchBinding
import com.android.bottomsheetSample.databinding.FragmentViewBinding

class OnSearchFragment : Fragment() {

//    reference
//    https://stackoverflow.com/questions/37715822/android-viewpager-with-recyclerview-works-incorrectly-inside-bottomsheet

    private var _binding: FragmentOnSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var onSearchPagerAdapter: OnSearchPagerAdapter

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataManagement.onSearch += 1
        _binding?.tvName?.text = "On Search - ${DataManagement.onSearch}"

        val tabTitles = arrayOf("QUESTIONS", "POSTS", "CHAPTERS")
        onSearchPagerAdapter = OnSearchPagerAdapter(tabTitles,
                childFragmentManager,
        )
        _binding?.vpOnSearch?.apply {
            adapter = onSearchPagerAdapter
            offscreenPageLimit = 3
//            isNestedScrollingEnabled = true
        }

        _binding?.vpOnSearch?.let {
            _binding?.tlOnSearch?.setupWithViewPager(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}