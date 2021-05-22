package com.android.bottomsheetSample.subStackFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.android.bottomsheetSample.DataManagement
import com.android.bottomsheetSample.databinding.ExploreChaptersBinding
import com.android.bottomsheetSample.fragments.print

class ExploreChapters : Fragment() {

    private var _binding: ExploreChaptersBinding? = null
    private val binding get() = _binding!!

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = ExploreChaptersBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataManagement.exploreChapters += 1
//        _binding?.tvName?.text = "Explore Chapters - ${DataManagement.exploreChapters}"
//        _binding?.tvName?.setOnClickListener {
//            it.findNavController().navigate(R.id.on_search)
//        }

        _binding?.rvChapters?.adapter = SimpleAdapter(DataManagement.getDummyData())
        DataManagement.mKistState?.let {
            _binding?.rvChapters?.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        "lifecycle_change onDestroy".print()
    }

    override fun onDetach() {
        super.onDetach()
        "lifecycle_change onDetach".print()
    }

    fun saveState(){
        _binding?.rvChapters?.layoutManager?.let {
            DataManagement.mKistState = it.onSaveInstanceState()
//            outState.putParcelable(LIST_STATE_KEY, DataManagement.mKistState);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveState()
        "lifecycle_change onDestroyView".print()
        _binding = null
//        DataManagement.mKistState = null
    }

    companion object{
        const val LIST_STATE_KEY = "LIST_STATE_KEY"
    }

}

