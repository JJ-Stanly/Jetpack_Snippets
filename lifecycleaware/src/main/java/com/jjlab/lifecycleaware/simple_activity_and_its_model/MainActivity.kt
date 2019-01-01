package com.jjlab.lifecycleaware.simple_activity_and_its_model

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjlab.lifecycleaware.R
import com.jjlab.lifecycleaware.activity_fragment_shared_viewmodel.HostActivity
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

        // Next activity
        act_main_btn_shared_view_model.setOnClickListener {
            val intentSharedViewModel = Intent(this, HostActivity::class.java)
            startActivity(intentSharedViewModel)
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
