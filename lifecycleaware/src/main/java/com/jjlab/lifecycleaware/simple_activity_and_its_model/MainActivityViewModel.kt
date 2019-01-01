package com.jjlab.lifecycleaware.simple_activity_and_its_model

import android.os.Bundle
import androidx.lifecycle.*

class MainActivityViewModel(private var count: Int = 0) : ViewModel(), LifecycleObserver {

    companion object {
        const val COUNT_KEY = "count_key"
    }

    val changeNotifier = MutableLiveData<Int>()

    fun increment() {
        changeNotifier.value = ++count
    }

    fun saveState(outState: Bundle?) {
        outState?.putInt(COUNT_KEY, count)
    }

    fun restoreState(inState: Bundle?) {
        inState?.let { count = it.getInt(COUNT_KEY) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        increment()
    }


}