package com.akash.bmicalculator2.bmis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akash.bmicalculator2.EventObserver
import com.akash.bmicalculator2.MainActivity
import com.akash.bmicalculator2.R
import com.akash.bmicalculator2.databinding.FragmentBmisBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private const val TAG = "BmisFragment"

class BmisFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BmisViewModel
//    private val viewModel by viewModels<BmisViewModel>()

    private lateinit var binding: FragmentBmisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BmisViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmisBinding.inflate(layoutInflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupListAdapter()
        onFabCLick()
        setupDeleteBmiDialog()
    }

    private fun setupDeleteBmiDialog() {
        viewModel.deleteBmiDialog.observe(viewLifecycleOwner, EventObserver { _bmi ->
            context?.let { _context ->
                val dialog = BottomSheetDialog(_context)
                dialog.setContentView(
                    this.layoutInflater.inflate(
                        R.layout.dialog_bottomsheet_deleteitem,
                        null
                    )
                )
                dialog.findViewById<AppCompatButton>(R.id.btn_cancel)?.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.findViewById<AppCompatButton>(R.id.btn_delete)?.setOnClickListener {
                    viewModel.deleteBmi((_bmi))
                    dialog.dismiss()
                }
                dialog.show()
            }
        })
    }

    private fun onFabCLick() {
        binding.fabCalculateNewBmi.setOnClickListener {
            val action = BmisFragmentDirections.actionBmisFragmentToAddNewBmiFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupListAdapter() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            binding.recyclerView.adapter = BmisListAdapter(viewmodel)
        } else {
            Log.w(
                "No init ViewModel",
                "ViewModel not initialized when attempting to set up adapter."
            )
        }
    }
}