package com.aldi.coin.data.service.response

open class BaseResponse<out T>(val timestamp: String? = null, val data : T? = null)
