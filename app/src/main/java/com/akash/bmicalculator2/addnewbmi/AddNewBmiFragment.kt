package com.akash.bmicalculator2.addnewbmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akash.bmicalculator2.EventObserver
import com.akash.bmicalculator2.databinding.FragmentAddNewBmiBinding
import com.akash.bmicalculator2.utils.setupSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewBmiFragment : Fragment() {

    private lateinit var binding: FragmentAddNewBmiBinding
    private val viewModel by viewModels<AddNewBmiViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNewBmiBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackBar()
        setupNavigation()
    }

    private fun setupSnackBar() {
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    private fun setupNavigation() {
        viewModel.calculateBmiEvent.observe(viewLifecycleOwner, EventObserver {
            val action =
                AddNewBmiFragmentDirections.actionAddNewBmiFragmentToResultFragment(viewModel.bmi)
            findNavController().navigate(action)
        })
    }
}