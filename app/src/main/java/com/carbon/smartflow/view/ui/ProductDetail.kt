package com.carbon.smartflow.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.carbon.smartflow.adapter.ColorAdapter
import com.carbon.smartflow.data.model.Product
import com.carbon.smartflow.databinding.ProductDetailFragmentBinding

class ProductDetail : Fragment() {
    private var _binding : ProductDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product : Product = arguments?.getParcelable("product")!!
        binding.title.text = product.name
        displayProductInfo(product)
        //navigates back to the previous screen
        binding.backBT.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun displayProductInfo(product: Product) {
        binding.productBrand.text = product.brand
        binding.productName.text = product.name
        binding.productType.text = product.product_type
        binding.productDescription.text = product.description

        Glide.with(requireView()).load(product.image_link).into(binding.productImg)

        val pColors = product.product_colors
        binding.colorRecyclerview.layoutManager = GridLayoutManager(requireContext(), 8)
        binding.colorRecyclerview.adapter = ColorAdapter(requireContext(), pColors)
    }

}