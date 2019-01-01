package com.jjlab.lifecycleaware.activity_fragment_shared_viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val seekBarValue: MutableLiveData<Int> = MutableLiveData()

}