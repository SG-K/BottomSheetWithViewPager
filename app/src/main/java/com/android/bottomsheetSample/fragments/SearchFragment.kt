package com.android.bottomsheetSample.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.bottomsheetSample.DataManagement
import com.android.bottomsheetSample.R
import com.android.bottomsheetSample.databinding.FragmentSearchBinding
import com.android.bottomsheetSample.databinding.FragmentViewBinding
import com.android.bottomsheetSample.subStackFragments.ExploreChapters
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.let {
            bottomSheetBehavior = BottomSheetBehavior.from(it.layouutBottomSheet.bottomSheet)
            initListeners()
            bottomSheetBehavior.isFitToContents = false
            bottomSheetBehavior.halfExpandedRatio = 0.6f
            bottomSheetBehavior.state = DataManagement.bottomSheetState
        }
        DataManagement.searchInstance += 1
        _binding?.tvName?.text = "Search - ${DataManagement.searchInstance}"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, true) {
            when {
//                _binding?.layouutBottomSheet?.navHostContainer?.findNavController()?.currentDestination?.id == R.id.on_search -> {
//                    _binding?.layouutBottomSheet?.navHostContainer?.let{
//                        if (it.findNavController().currentDestination?.id == R.id.on_search) {
//                            it.findNavController().navigateUp()
//                            DataManagement.isShowingSearch = false
//                        }
//                    }
//                }
                bottomSheetBehavior.state != BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    isEnabled = false
                }
                else -> {
                    isEnabled = false
                    requireActivity().finish()
                }
            }
        }
    }

    fun initListeners(){

        _binding?.layouutBottomSheet?.imBack?.setOnClickListener {
            if (::bottomSheetBehavior.isInitialized){
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HALF_EXPANDED)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }

        _binding?.layouutBottomSheet?.tvHeader?.setOnClickListener {
            _binding?.layouutBottomSheet?.navHostContainer?.let {
                if (it.findNavController().currentDestination?.id == R.id.explore_chapter){
                    it.findNavController().navigate(R.id.on_search)
                    DataManagement.isShowingSearch = true
                }
            }
        }

        _binding?.tvName?.setOnClickListener {
            if (::bottomSheetBehavior.isInitialized){
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HALF_EXPANDED)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                else
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                "Current_state_track - $newState".print()
                DataManagement.bottomSheetState = newState
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

fun Any.print(){
    Log.v("sampleTestsLog", "$this")
}