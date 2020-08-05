package com.rodrigo.buxtrading.data.model

data class ApiHttpError(
    val message: String,
    val developerMessage: String,
    val errorCode: String
)