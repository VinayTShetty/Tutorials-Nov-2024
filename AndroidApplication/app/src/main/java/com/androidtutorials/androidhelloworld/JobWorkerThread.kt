package com.androidtutorials.androidhelloworld

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

/**
 * STEP 11
 * Background Worker Thread
 */
class JobWorkerThread(
    private val jobService: JobService,
    private val jobParameters: JobParameters?
) : Thread() {

    companion object {
        const val FLOW_TAG = "JOB_FLOW"
        const val TAG = "JOB_WORKER_THREAD"
    }

    override fun run() {

        Log.d(FLOW_TAG, "WorkerThread: Background work started")
        Log.d(TAG, "run() invoked")

        try {
            sleep(3000) // Simulated long work
        } catch (e: InterruptedException) {
            Log.d(FLOW_TAG, "WorkerThread interrupted")
            Log.d(TAG, "Thread interrupted")
            return
        }

        Log.d(FLOW_TAG, "WorkerThread: Background work completed")
        Log.d(TAG, "Calling jobFinished()")

        jobService.jobFinished(jobParameters, false)
    }
}
