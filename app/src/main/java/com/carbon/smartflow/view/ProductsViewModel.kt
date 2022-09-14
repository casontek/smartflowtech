package com.carbon.smartflow.view

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carbon.smartflow.data.model.Product
import com.carbon.smartflow.data.network.Repository
import com.carbon.smartflow.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var products : List<Product>? = null

    fun getProducts() : Flow<Result<List<Product>>> = flow {
        emit(Result.loading())
        try {
            repository.getProducts().let {
                if(it.isSuccessful) {
                    val products = it.body()
                    if(products != null) {
                        emit(Result.success("success", products))
                    }
                    else emit(Result.error("empty product!"))
                    Timber.i("data response[Timber]: ${it.body()}")
                }
                else {
                    emit(Result.error(it.message()))
                }
                Timber.i("network response[Timber]: ${it.raw().networkResponse()}")
            }
        }
        catch (e: Exception) {
            emit(Result.error("network issue."))
            Timber.i("catch error[Timber]: $e")
        }
    }

    fun setFetchedProducts(data : List<Product>) {
        products = data
    }

    fun getFetchedProducts() =  products

}