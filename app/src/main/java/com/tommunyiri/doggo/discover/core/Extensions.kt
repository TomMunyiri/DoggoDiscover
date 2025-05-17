package com.tommunyiri.doggo.discover.core

import android.content.Context
import com.tommunyiri.doggo.discover.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private val somethingWentWrong = R.string.something_went_wrong
private val apiError = R.string.api_error
private val notAuthorized = R.string.not_authorized
private val networkProblem = R.string.network_problem
private val pleaseCheckYourInternet = R.string.check_internet
private val letsTryAgain = R.string.lets_try_again
private val badRequest = R.string.bad_request

fun Throwable.handleException(): Pair<Int, Any> {
    val errorPair: Pair<Int, Any> = when {
        this is ApiException -> somethingWentWrong to this.statusMessage
        this is UnknownHostException -> networkProblem to pleaseCheckYourInternet
        this is HttpException && this.code() == HttpStatusCodes.FORBIDDEN -> somethingWentWrong to notAuthorized
        this is HttpException && this.code() in HttpStatusCodes.INTERNAL_SERVER_ERROR..599 -> somethingWentWrong to apiError
        this is HttpException && this.code() == HttpStatusCodes.BAD_REQUEST -> somethingWentWrong to badRequest
        this is SocketTimeoutException -> networkProblem to pleaseCheckYourInternet
        else -> letsTryAgain to apiError
    }
    return errorPair
}

fun Any.anyToString(context: Context): String {
    return when (this) {
        is String -> this.toString()
        is Int -> context.getString(this)
        else -> ""
    }
}

class ApiException(val statusCode: Int = 0, val statusMessage: String) : Throwable(statusMessage)