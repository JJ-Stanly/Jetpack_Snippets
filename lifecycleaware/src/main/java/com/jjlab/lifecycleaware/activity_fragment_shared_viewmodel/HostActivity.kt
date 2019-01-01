package com.jjlab.lifecycleaware.activity_fragment_shared_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjlab.lifecycleaware.R

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
    }
}
