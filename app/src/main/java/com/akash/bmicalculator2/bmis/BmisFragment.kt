package com.akash.bmicalculator2.bmis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.akash.bmicalculator2.MainActivity
import com.akash.bmicalculator2.databinding.FragmentBmisBinding

private const val TAG = "BmisFragment"

class BmisFragment : Fragment() {

    private val viewModel by viewModels<BmisViewModel>()

    //    private lateinit var binding: FragmentBmisBinding
    private lateinit var binding: FragmentBmisBinding

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmisBinding.inflate(layoutInflater, container, false)
//        binding.viewmodel = viewModel
        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.items.observe(viewLifecycleOwner, Observer { listOfBmis ->
            Log.d(TAG, "onActivityCreated: List of Bmis: $listOfBmis")
        })
//        binding.lifecycleOwner = viewLifecycleOwner
        onFabCLick()
    }

    private fun onFabCLick() {
        binding.fabCalculateNewBmi.setOnClickListener {
            val action = BmisFragmentDirections.actionBmisFragmentToAddNewBmiFragment()
            findNavController().navigate(action)
        }
    }
}