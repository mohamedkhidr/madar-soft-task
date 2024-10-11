package com.khidrew.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.khidrew.task.R
import com.khidrew.task.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupSpinner()
        binding.btnSave.setOnClickListener {
            viewModel.isSaveClicked.set(true)
            if (viewModel.validate()) {
                viewModel.saveUser()
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collectLatest {
                    if (it.statusInserted) {
                        Toast.makeText(requireContext(), R.string.data_inserted, Toast.LENGTH_LONG)
                            .show()
                    }

                    if (it.isError) {
                        it.message?.let { message ->
                            Toast.makeText(
                                requireContext(),
                                message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }

        setupOptionMenu()
    }

    private fun setupOptionMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.history -> {
                        findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_list,
            android.R.layout.simple_spinner_dropdown_item
        ).also {
            binding.actGender.setAdapter(it)
        }

    }


}