package com.jjlab.roompersistence


import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjlab.roompersistence.data.WeatherDatabase
import com.jjlab.roompersistence.data.WeatherEntity
import com.jjlab.roompersistence.viewmodels.RoomMainViewModel

class RoomMainActivity : AppCompatActivity() {


    private val viewModel by lazy {
        ViewModelProviders.of(this).get(RoomMainViewModel::class.java)
    }

    private val weatherEntityListObserver = Observer<List<WeatherEntity>> {
        setValuesFromDatabase(it)
    }

    private var weatherDatabase: WeatherDatabase? = null

    private lateinit var txtWeatherDetails: TextView
    private lateinit var btnGetData: Button
    private lateinit var btnSaveData: Button

    private val mUiHandler = Handler()

    private var count: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)
        lifecycle.addObserver(viewModel)
        //viewModel.weatherEntityListNotifier.observe(this, weatherEntityListObserver)


        txtWeatherDetails = findViewById(R.id.act_room_main_txt)

        weatherDatabase = WeatherDatabase.getInstance(this)

        btnGetData = findViewById(R.id.act_room_main_btn_get_data)
        btnGetData.setOnClickListener {
            //getDataFromDatabase()
        }

        btnSaveData = findViewById(R.id.act_room_main_btn_save_data)
        btnSaveData.setOnClickListener {
            //insertDataIntoDatabase(makeSampleData())
        }



    }

//    private fun makeSampleData(): WeatherEntity {
//        var entity = WeatherEntity(id = count, humidity = 54, tempInc = 23.0, name = "Kochi, ${count.toString()}", cloud = "Partial")
//        count++
//        return entity
//    }

//    private fun bindDataWithUI(weatherEntity: WeatherEntity) {
//
//        txtWeatherDetails.text = "Name: ${weatherEntity.name}" +
//                "\n Temp: ${weatherEntity.tempInc} c\n" +
//                "Humidity: ${weatherEntity.humidity}%"
//    }

    private fun setValuesFromDatabase(list: List<WeatherEntity>) {
        var data: String? = null
        for (item in list) {
            data += "Name: ${item.name}" +
                    "\n Temp: ${item.tempInc} c\n" +
                    "Humidity: ${item.humidity}%"
        }
    }

//    private fun insertDataIntoDatabase(weatherEntity: WeatherEntity) {
//        Log.d(TAG, "WeatherEntity-count: ${weatherEntity.name}")
//        val task = Runnable { weatherDatabase?.weatherDao()?.insert(weatherEntity) }
//        databaseWorkerThread.postTask(task)
//    }

//    private fun getDataFromDatabase() {
//        val task = Runnable {
//            val weatherEntity = weatherDatabase?.weatherDao()?.getAll()
//            mUiHandler.post {
//                if (weatherEntity == null || weatherEntity?.size == 0) {
//                    Log.i(TAG, "No data in cache!")
//                } else {
//                    Log.d(TAG, "Size: ${weatherEntity?.size}")
//                    bindDataWithUI(weatherEntity?.let { it.get(it.size - 1) })
//                }
//            }
//        }
//
//        databaseWorkerThread.postTask(task)
//
//    }

//    override fun onDestroy() {
//        WeatherDatabase.destroyInstance()
//        databaseWorkerThread.quit()
//        super.onDestroy()
//    }

}
