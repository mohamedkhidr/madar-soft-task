package com.khidrew.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.khidrew.domian.entities.InfoModel
import com.khidrew.task.databinding.FragmentSecondBinding
import com.khidrew.task.infoItemList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: MainViewModel by viewModels()
    private var list: ArrayList<InfoModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collectLatest {
                    if (it.status) {
                        it.data?.let { it1 ->
                            list.clear()
                            list.addAll(it1)
                        }
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

                    listEpoxy()
                }
            }
        }
    }

    private fun listEpoxy() {
        binding.rvItems.withModels {
            if (view == null)
                return@withModels

            if (list.isEmpty()) {
                return@withModels
            }

            lifecycleScope.launch {
                list.forEachIndexed { index, data ->
                    infoItemList {
                        id("info $index")
                        data(data)
                    }
                }
            }

        }


    }

}