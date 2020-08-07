package com.akash.bmicalculator2.result

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akash.bmicalculator2.EventObserver
import com.akash.bmicalculator2.databinding.FragmentResultBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private const val TAG = "ResultFragment"

class ResultFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ResultViewModel

    //    private val viewModel by viewModels<ResultViewModel>()
    private lateinit var binding: FragmentResultBinding
    private val safeArgs: ResultFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: SafeArgs: ${safeArgs.bmi}")
        viewModel.getBmi(safeArgs.bmi)

        setupRecalculateBmi()
        setNavigationToBmiFragment()
    }

    private fun setupRecalculateBmi() {
        binding.btnRecalculate.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAddNewBmiFragment()
            findNavController().navigate(action)
        }
    }

    private fun setNavigationToBmiFragment() {
/*
        binding.btnSaveBmi.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAddNewBmiFragment()
            findNavController().navigate(action)
        }
*/

        viewModel.saveBmiEvent.observe(viewLifecycleOwner, EventObserver {
            val action = ResultFragmentDirections.actionResultFragmentToBmisFragment()
            findNavController().navigate(action)
        })
    }
}