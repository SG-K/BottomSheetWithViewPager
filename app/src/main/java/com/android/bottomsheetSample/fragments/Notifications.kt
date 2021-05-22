package com.android.bottomsheetSample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.android.bottomsheetSample.DataManagement
import com.android.bottomsheetSample.databinding.FragmentViewBinding

class Notifications  : Fragment() {

    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DataManagement.notificationsInstance += 1
        _binding?.tvName?.text = "Notifications - ${DataManagement.notificationsInstance}"
    }

}