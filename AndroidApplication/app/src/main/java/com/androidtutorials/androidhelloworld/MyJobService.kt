package com.androidtutorials.androidhelloworld

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

/**
 * STEP 8
 * JobService
 * ----------
 * • Started by Android system
 * • Runs on MAIN THREAD by default
 */
class MyJobService : JobService() {

    companion object {
        const val FLOW_TAG = "JOB_FLOW"
        const val TAG = "JOB_SERVICE"
    }

    /**
     * Worker thread reference
     */
    private lateinit var workerThread: JobWorkerThread

    /**
     * STEP 9
     * Called when job starts
     */
    override fun onStartJob(params: JobParameters?): Boolean {

        Log.d(FLOW_TAG, "JobService started by system")
        Log.d(TAG, "onStartJob() called")

        startWorkerThread(params)

        /**
         * true → work continues asynchronously
         */
        return true
    }

    /**
     * STEP 10
     * Start background thread
     */
    private fun startWorkerThread(params: JobParameters?) {

        workerThread = JobWorkerThread(
            jobService = this,
            jobParameters = params
        )

        workerThread.start()

        Log.d(FLOW_TAG, "Worker thread started from JobService")
        Log.d(TAG, "startWorkerThread() executed")
    }

    /**
     * STEP 13
     * Called if system stops job
     */
    override fun onStopJob(params: JobParameters?): Boolean {

        Log.d(FLOW_TAG, "JobService stopped by system")
        Log.d(TAG, "onStopJob() called")

        if (::workerThread.isInitialized) {
            workerThread.interrupt()
        }

        /**
         * true → retry later
         */
        return true
    }
}
