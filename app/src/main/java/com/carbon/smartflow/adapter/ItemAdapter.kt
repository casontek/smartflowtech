package com.carbon.smartflow.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carbon.smartflow.R
import com.carbon.smartflow.data.model.Product
import com.carbon.smartflow.databinding.ItemInflatorBinding

class ItemAdapter(
    val c: Context,
    val items: List<Product>
) : RecyclerView.Adapter<ItemAdapter.ItemHolder>(){

    class ItemHolder(val binding: ItemInflatorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(
            ItemInflatorBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val productItem = items[position]
        //display the products
        holder.binding.itemCategory.text = productItem.category
        holder.binding.itemName.text = productItem.name
        holder.binding.itemPrice.text = "${productItem.price_sign} ${productItem.price}"
        //displays the image of the product
        Glide.with(c).load(productItem.image_link).into(holder.binding.itemImg)
        Log.d("_&photo", "picture label: ${productItem.image_link}")
        //navigates to the product detail page
        holder.itemView.setOnClickListener {
            val b = Bundle()
            b.putParcelable("product", productItem)
            Navigation.findNavController(holder.itemView).navigate(R.id.productDetail, b)
        }

    }

    override fun getItemCount(): Int = items.size
}