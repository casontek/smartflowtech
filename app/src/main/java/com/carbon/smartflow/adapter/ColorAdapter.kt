package com.carbon.smartflow.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carbon.smartflow.data.model.ProductColor
import com.carbon.smartflow.databinding.ColorInflatorBinding
import com.google.android.material.snackbar.Snackbar

class ColorAdapter(
    private val c: Context,
    private val productColors: List<ProductColor>) :
    RecyclerView.Adapter<ColorAdapter.ColorHolder>() {

    class ColorHolder(val binding: ColorInflatorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder =
        ColorHolder(
            ColorInflatorBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
        val namedColor = productColors[position]
        holder.binding.colorBox.setBackgroundColor(Color.parseColor(namedColor.hex_value))
        holder.itemView.setOnClickListener {
            Snackbar.make(
                holder.itemView,
                "COLOR: ${namedColor.colour_name}",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun getItemCount(): Int = productColors.size
}