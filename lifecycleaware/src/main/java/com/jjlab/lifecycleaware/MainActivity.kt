package com.jjlab.lifecycleaware

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    private val changeObserver = Observer<Int> {
        setTimerValue(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.restoreState(savedInstanceState)

        // Making viewModel aware of lifecycle of this activity
        lifecycle.addObserver(viewModel)

        viewModel.changeNotifier.observe(this, changeObserver)

        act_main_btn.setOnClickListener {
            viewModel.increment()
        }
    }

    private fun setTimerValue(value: Int) {
        act_main_timer.text = value.toString()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        viewModel.saveState(outState)
    }

}
