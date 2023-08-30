package com.techhabiles.jokes

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@JvmOverloads
fun<T> LiveData<T>.getOrAwaitValue(time: Long =2,
                                   timeUnit: TimeUnit = TimeUnit.SECONDS,
                                   afterObserver: ()-> Unit = {}): T{
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T>{
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    afterObserver.invoke()
    if(!latch.await(time, timeUnit)){
        this.removeObserver(observer)
        throw TimeoutException("Live data value was not set")
    }
    return data as T
}