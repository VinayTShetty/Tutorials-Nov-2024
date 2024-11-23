package com.androidtutorials.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class RandomNumberWorker(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {

    private val TAG=RandomNumberWorker::class.simpleName
    override fun doWork(): Result {
        val randomNumber= Random.nextInt(0,100)
        Log.d(TAG, "doWork: ${randomNumber}")
        return Result.success()
    }

}