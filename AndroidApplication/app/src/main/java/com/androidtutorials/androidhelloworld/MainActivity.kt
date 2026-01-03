package com.androidtutorials.androidhelloworld

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.File

/**
 * STEP 1
 * Activity Entry Point
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "DOWNLOAD_FLOW"

    /**
     * STEP 0
     * Class-level variables
     */
    private lateinit var downloadManager: DownloadManager
    private lateinit var downloadRequest: DownloadManager.Request

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "STEP 1: onCreate called")

        /**
         * STEP 2
         * Setup UI
         */
        setContentView(R.layout.activity_main)

        Log.d(TAG, "STEP 2: UI setup complete")

        /**
         * STEP 3
         * Start Download Flow
         */
        startDownloadFlow()
    }

    /**
     * STEP 4
     * Download Flow Controller
     */
    private fun startDownloadFlow() {

        Log.d(TAG, "STEP 4: Starting download flow")

        initDownloadManager()
        initDownloadRequest()
        configureDownloadRequest()
        setDownloadDestination()
        enqueueDownload()
    }

    /**
     * STEP 5
     * Initialize DownloadManager
     */
    private fun initDownloadManager() {
        downloadManager =
            getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        Log.d(TAG, "STEP 5: DownloadManager initialized")
    }

    /**
     * STEP 6
     * Initialize Download Request
     */
    private fun initDownloadRequest() {
        downloadRequest = DownloadManager.Request(
            Uri.parse("https://picsum.photos/300/300")
        )

        Log.d(TAG, "STEP 6: Image download request created")
    }

    /**
     * STEP 7
     * Configure Download Request
     */
    private fun configureDownloadRequest() {
        downloadRequest.setTitle("Image Download")
        downloadRequest.setDescription("Downloading image...")
        downloadRequest.setNotificationVisibility(
            DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
        )

        Log.d(TAG, "STEP 7: Download request configured")
    }

    /**
     * STEP 8
     * Set Download Destination Path
     */
    private fun setDownloadDestination() {

        val fileName = "sample_image.jpg"

        val downloadDir = getExternalFilesDir(null)
        val filePath = File(downloadDir, fileName).absolutePath

        downloadRequest.setDestinationInExternalFilesDir(
            this,
            null,
            fileName
        )

        Log.d(TAG, "STEP 8: Download destination set")
        Log.d(TAG, "IMAGE WILL BE SAVED AT: $filePath")
    }

    /**
     * STEP 9
     * Enqueue Download
     */
    private fun enqueueDownload() {
        downloadManager.enqueue(downloadRequest)

        Log.d(TAG, "STEP 9: Image download enqueued")
        Log.d(TAG, "Android OS is downloading the image in background")
    }
}
