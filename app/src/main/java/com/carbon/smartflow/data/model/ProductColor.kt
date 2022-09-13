package com.carbon.smartflow.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductColor(
    val hex_value: String,
    val colour_name: String
) : Parcelable
