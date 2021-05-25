package com.example.turbocaretestassignment.rhs.app.base

/**
 * @author yash gupta
 *
 * This class is used as wrapper class when a API call is made so that status tracking can be done.
 * It should be used closely with @see LiveData
 */
class LiveDataResultWrapper<T> {
    var resultData : T? = null
    var error: Throwable? = null
    var resultStatus : LiveDataResultStatus? = LiveDataResultStatus.LOADING
}

/**
 * LiveData Result status
 */
enum class LiveDataResultStatus {
    LOADING, // when api is just called
    COMPLETE, // api call is completed successfully
    ERROR // api call completed with some error
}