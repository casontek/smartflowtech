package com.carbon.smartflow.utils

class Result<out T>(
    val status: Status,
    val message: String?,
    val data: T?
) {
    companion object {

        fun <T> loading() : Result<T> {
            return Result(Status.Loading, null, null)
        }

        fun <T> success(message: String, data: T?) : Result<T> {
            return Result(Status.Success, message, data)
        }

        fun <T> error(message: String) : Result<T> {
            return Result(Status.Error, message, null)
        }

    }
}