package com.jjlab.roompersistence.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jjlab.roompersistence.data.WeatherDatabase
import com.jjlab.roompersistence.data.WeatherEntity
import java.lang.Exception

class DatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val TAG by lazy { DatabaseWorker::class.java.simpleName }

    override fun doWork(): Result {
        return try {
            val database = WeatherDatabase.getInstance(applicationContext)
            database.weatherDao().insert(makeSampleData())
            Log.i(TAG, "Database seeding is success.")
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Database seeding is failed with exception ${ex}")
            Result.failure()
        }
    }

    private fun makeSampleData(): WeatherEntity {
        var count: Long = 0
        var entity = WeatherEntity(id = count, humidity = 54, tempInc = 23.0, name = "Kochi, ${count.toString()}", cloud = "Partial")
        count++
        return entity
    }

}