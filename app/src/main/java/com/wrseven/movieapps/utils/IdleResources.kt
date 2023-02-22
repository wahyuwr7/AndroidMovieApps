package com.wrseven.movieapps.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdleResources {
    private const val RESOURCE: String = "GLOBAL"
    private val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = espressoIdlingResource.increment()
    fun decrement() = espressoIdlingResource.decrement()
    fun getIdleResource(): CountingIdlingResource = espressoIdlingResource
}