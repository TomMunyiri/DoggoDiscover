package com.tommunyiri.doggo.discover.core


const val GRID_ITEM_COUNT = 2
const val INITIAL_LIST_PAGE = 0
const val INITIAL_LIST_LIMIT = 14

/**
 * [HttpStatusCodes] is a set of pre-defined constants for commonly used status codes.
 * It's designed to simplify HTTP-related development by offering a consistent and easy-to-use API.
 */
object HttpStatusCodes {
    @Suppress("UNUSED")
    const val OK = 200 // OK
    const val BAD_REQUEST = 400 // Bad Request
    const val FORBIDDEN = 403 // Forbidden
    const val INTERNAL_SERVER_ERROR = 500 // Internal Server Error
}