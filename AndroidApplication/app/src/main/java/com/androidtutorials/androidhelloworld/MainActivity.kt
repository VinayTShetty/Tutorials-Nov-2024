package com.androidtutorials.androidhelloworld

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

/**
 * STEP 1
 * Activity Entry Point
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val FLOW_TAG = "JOB_FLOW"
        const val TAG = "JOB_SCHEDULER"
    }

    /**
     * Class-level objects
     */
    private lateinit var jobScheduler: JobScheduler
    private lateinit var jobInfo: JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(FLOW_TAG, "MainActivity created")

        /**
         * STEP 2
         * Setup UI
         */
        setContentView(R.layout.activity_main)

        /**
         * STEP 3
         * Start Job scheduling flow
         */
        startJobSchedulingFlow()
    }

    /**
     * STEP 4
     * Job scheduling controller
     */
    private fun startJobSchedulingFlow() {
        Log.d(FLOW_TAG, "Starting job scheduling flow")

        initJobScheduler()
        createJobInfo()
        scheduleJob()
    }

    /**
     * STEP 5
     * Initialize JobScheduler system service
     */
    private fun initJobScheduler() {
        jobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        Log.d(FLOW_TAG, "JobScheduler obtained from system")
        Log.d(TAG, "JobScheduler initialized")
    }

    /**
     * STEP 6
     * Create JobInfo (defines job constraints)
     */
    private fun createJobInfo() {

        val componentName = ComponentName(
            this,
            MyJobService::class.java
        )

        jobInfo = JobInfo.Builder(1, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPersisted(false)
            .build()

        Log.d(FLOW_TAG, "JobInfo created")
        Log.d(TAG, "JobInfo configured")
    }

    /**
     * STEP 7
     * Schedule job with Android OS
     */
    private fun scheduleJob() {
        jobScheduler.schedule(jobInfo)

        Log.d(FLOW_TAG, "Job scheduled with Android OS")
        Log.d(TAG, "schedule() called")
    }
}
