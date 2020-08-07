package com.akash.bmicalculator2.addnewbmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akash.bmicalculator2.EventObserver
import com.akash.bmicalculator2.databinding.FragmentAddNewBmiBinding
import com.akash.bmicalculator2.utils.setupSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AddNewBmiFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AddNewBmiViewModel

    //    private val viewModel by viewModels<AddNewBmiViewModel>()
    private lateinit var binding: FragmentAddNewBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddNewBmiViewModel::class.java)
    }

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