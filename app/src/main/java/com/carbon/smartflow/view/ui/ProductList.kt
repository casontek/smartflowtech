package com.carbon.smartflow.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.carbon.smartflow.adapter.ProductAdapter
import com.carbon.smartflow.data.model.Product
import com.carbon.smartflow.databinding.ProductListFragmentBinding
import com.carbon.smartflow.utils.Status
import com.carbon.smartflow.view.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductList : Fragment() {
    private var _binding : ProductListFragmentBinding? = null
    private val binding get() = _binding!!
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loads products from the server
        loadData()
        binding.retryBT.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        productsViewModel.viewModelScope.launch {
            if(productsViewModel.getFetchedProducts() == null) {
                productsViewModel.getProducts().collect {
                    when (it.status) {
                        Status.Loading -> toggleUI(View.VISIBLE, View.GONE)
                        Status.Success -> {
                            toggleUI(View.GONE, View.GONE)
                            val data = it.data!!
                            organiseFetchedProducts(data)
                            productsViewModel.setFetchedProducts(data)
                        }
                        Status.Error -> {
                            binding.errorMessage.text = it.message ?: "failed. try again!"
                            toggleUI(View.GONE, View.VISIBLE)
                        }
                    }
                }
            }
            else {
                organiseFetchedProducts(productsViewModel.getFetchedProducts()!!)
            }
        }
    }

    private fun organiseFetchedProducts(data: List<Product>){
        //organize the products by brands
        data.map { it.brandType = "${it.brand}_${it.product_type}" }
        Log.d("_&product", "data: ${data[0]}")

        data.sortedBy { it.brandType }
        val groupedData = data.groupBy { it.brandType }
        val groupedByBrandType = arrayListOf<List<Product>>()
        groupedData.forEach { (t, u) -> groupedByBrandType.add(u) }
        //display on the outer recyclerview
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ProductAdapter(requireContext(), groupedByBrandType)
        binding.recyclerview.adapter = adapter

    }

    private fun toggleUI(progressState: Int, messageState: Int) {
        binding.loadingProgrss.visibility = progressState
        binding.errorContainer.visibility = messageState
    }

}