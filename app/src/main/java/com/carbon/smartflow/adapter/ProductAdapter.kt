package com.carbon.smartflow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carbon.smartflow.data.model.Product
import com.carbon.smartflow.databinding.ProductInflatorBinding

class ProductAdapter(
    private val c: Context,
    private val products: ArrayList<List<Product>>
) : RecyclerView.Adapter<ProductAdapter.BrandHolder>() {
    var prevBrand: String = ""

    class BrandHolder(val binding: ProductInflatorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandHolder =
        BrandHolder(
            ProductInflatorBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BrandHolder, position: Int) {
        val product = products[position]
        val brand = product[0].brand
        holder.binding.productBrand.text = brand
        holder.binding.typeName.text = product[0].product_type
        if(position == 0 || prevBrand != brand) {
            holder.binding.productBrand.visibility = View.VISIBLE
        }
        else {
            holder.binding.productBrand.visibility = View.GONE
        }
        prevBrand = brand
        //display the inner list
        holder.binding.brandRecyclerview.layoutManager = GridLayoutManager(c, 2)
        holder.binding.brandRecyclerview.adapter = ItemAdapter(c, product)

    }

    override fun getItemCount(): Int = products.size
}