package com.jjlab.lifecycleaware.activity_fragment_shared_viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.jjlab.lifecycleaware.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SeekbarFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SeekbarFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SeekbarFragment : Fragment() {

    private lateinit var seekBar: SeekBar
    private val seekBarValueObserver = Observer<Int> {
        seekBar.progress = it
    }
    private val sharedViewModel: SharedViewModel by lazy {
//        return@lazy when {
////            activity != null -> {
////                ViewModelProviders.of(activity as FragmentActivity).get(SharedViewModel::class.java)
////            } else -> {
////                ViewModelProviders.of(this).get(SharedViewModel::class.java)
////            }
////        }
        ViewModelProviders.of(activity as FragmentActivity).get(SharedViewModel::class.java)

    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_seekbar, container, false)
        seekBar = view.findViewById(R.id.frag_seekbar) as SeekBar
        subscribeToValueChanges()
        return view
    }

    fun subscribeToValueChanges() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sharedViewModel.seekBarValue.value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        sharedViewModel.seekBarValue.observe(activity as FragmentActivity,  seekBarValueObserver)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SeekbarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SeekbarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
